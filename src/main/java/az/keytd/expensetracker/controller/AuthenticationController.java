package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.dto.LoginRequest;
import az.keytd.expensetracker.dto.RegisterRequest;
import az.keytd.expensetracker.dto.Response;
import az.keytd.expensetracker.service.AuthenticationService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity signUp(RegisterRequest request) {
        Response response = authenticationService.register(request);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity signIn(LoginRequest request) {
        Response response = authenticationService.login(request);

        return ResponseEntity.ok(response);
    }
}
