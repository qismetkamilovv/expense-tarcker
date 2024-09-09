package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.dto.LoginRequest;
import az.keytd.expensetracker.dto.RegisterRequest;
import az.keytd.expensetracker.dto.Response;
import az.keytd.expensetracker.service.AuthenticationService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public Response signUp(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("login")
    public Response signIn(@RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @PutMapping("verify")
    public ResponseEntity<Void> verify(@RequestParam String email, @RequestParam String otp) {
        authenticationService.verify(email, otp);
        return ResponseEntity.ok().build();
    }
}
