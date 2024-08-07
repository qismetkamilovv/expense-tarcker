package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.dto.LoginRequest;
import az.keytd.expensetracker.dto.RegisterRequest;
import az.keytd.expensetracker.dto.Response;
import az.keytd.expensetracker.entities.User;
import az.keytd.expensetracker.security.JwtService;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CommonOtpService otpService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Response register(RegisterRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = userService.save(request, encodedPassword);
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
        otpService.checkOTP(email, otp);
        userService.verify(email);

    }
}
