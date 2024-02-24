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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OTPService otpService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Response register(RegisterRequest request) {
        User user = userService.save(request, passwordEncoder);
        String token = jwtService.generateToken(user);
        String otp = otpService.generateOtp();
        otpService.sendByEmail(user.getEmail(), otp);
        
        return new Response(200, "ok", token);

    }

    public Response login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userService.getByEmail(request.getEmail());
        String token = jwtService.generateToken(user);

        return new Response(200, "ok", token);
    }
}
