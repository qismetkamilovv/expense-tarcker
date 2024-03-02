package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.dto.LoginRequest;
import az.keytd.expensetracker.dto.RegisterRequest;
import az.keytd.expensetracker.dto.Response;
import az.keytd.expensetracker.entities.CommonOtp;
import az.keytd.expensetracker.entities.OtpStatus;
import az.keytd.expensetracker.entities.User;
import az.keytd.expensetracker.exceptions.BadRequestException;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.CommonOtpRepository;
import az.keytd.expensetracker.security.JwtService;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommonOtpService otpService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CommonOtpRepository commonOtpsRepository;

    public Response register(RegisterRequest request) {
        User user = userService.save(request, passwordEncoder);
        String token = jwtService.generateToken(user);
        otpService.sendByEmail(user.getEmail());
        return new Response(200, "ok", token);

    }

    public Response login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userService.getByEmail(request.getEmail());
        String token = jwtService.generateToken(user);

        return new Response(200, "ok", token);
    }

    public void verify(String email, String otp) {
        CommonOtp commonOtp = commonOtpsRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(email + " not found"));

        if (commonOtp.getOtp() != otp) {
            int count = commonOtp.getRetryCount() + 1;
            if (count >= 3) {
                commonOtp.setStatus(OtpStatus.FAILED);
                throw new BadRequestException("your otp expired");
            }
            commonOtp.setRetryCount(count);
            commonOtpsRepository.save(commonOtp);
            throw new BadRequestException("your otp code is not true");
        }
        commonOtp.setStatus(OtpStatus.CONFIRMED);
        commonOtpsRepository.save(commonOtp);

    }
}
