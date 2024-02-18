package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.dto.LoginRequest;
import az.keytd.expensetracker.dto.RegisterRequest;
import az.keytd.expensetracker.dto.Response;
import az.keytd.expensetracker.entities.Users;
import az.keytd.expensetracker.security.JwtService;

@Service
public class AuthenticationService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Response register(RegisterRequest request) {

        Users user = usersService.save(request);

        String token = jwtService.generateToken(user);
        
        // TODO send OTP via email here

        return new Response(200, "ok", token);

    }

    public Response login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

                // TODO extract  findbyEmail call to UserServices
        Users user = usersService.findByEmail(request.getEmail());
        String token = jwtService.generateToken(user);

        return new Response(200, "ok", token);
    }
}
