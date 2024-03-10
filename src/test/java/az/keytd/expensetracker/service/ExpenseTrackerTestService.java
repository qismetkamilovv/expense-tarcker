package az.keytd.expensetracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;

import az.keytd.expensetracker.dto.LoginRequest;
import az.keytd.expensetracker.dto.RegisterRequest;
import az.keytd.expensetracker.dto.Response;
import az.keytd.expensetracker.entities.CommonOtp;
import az.keytd.expensetracker.entities.OtpStatus;
import az.keytd.expensetracker.entities.User;
import az.keytd.expensetracker.repository.CommonOtpRepository;
import az.keytd.expensetracker.repository.UserRepository;
import az.keytd.expensetracker.security.JwtService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExpenseTrackerTestService {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private SimpleMailMessage simpleMailMessage;

    @MockBean
    private CommonOtpRepository commonOtpRepository;
    
    @MockBean
    private CommonOtpService otpService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    public void register_ShouldReturnToken() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("qismet600@gmail.com");
        registerRequest.setPassword("qismet123");

        User user = new User();
        user.setEmail("qismet600@gmail.com");
        user.setPassword("qismet123");

        String token = "token";

        when(userService.save(registerRequest, passwordEncoder)).thenReturn(user);

        when(jwtService.generateToken(user)).thenReturn(token);

        Response response = authenticationService.register(registerRequest);

        // verify(userService, times(1)).save(registerRequest, passwordEncoder);
        verify(jwtService, times(1)).generateToken(user);
        verify(otpService, times(1)).sendByEmail("qismet600@gmail.com");

        assertEquals(200, response.getStatus());
        assertEquals("ok", response.getMessage());
        assertEquals(token, response.getData());

    }
    @Test
    public void login_shouldLoginUser(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("qismet600@gmail.com");
        loginRequest.setPassword("qismet123");

        User user = new User();
        user.setEmail("qimet600@gmail.com");
        user.setPassword("qismet123");

        String token = "token";

        when(userService.getByEmail("qismet600@gmail.com")).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);

        Response response = authenticationService.login(loginRequest);

        // verify(userRepository, times(1)).getByEmail("qismet600@gmail.com");
        // verify(jwtService, times(1)).generateToken(user);

        assertEquals(200, response.getStatus());
        assertEquals("ok", response.getMessage());
        assertEquals(token, response.getData());

    }

    @Test
    public void sendByEmail_shouldSendOtp(){
        String to = "qismet600@gmail.com";

        when(otpService.generateOtp()).thenReturn("123456");

        otpService.sendByEmail(to);

        verify(otpService, times(1)).sendByEmail(to);
    }

    @Test
    public void verify_validOtp(){
        String email = "qismet600@gmail.com";
        String otp = "123456";

        CommonOtp commonOtp = new CommonOtp();
        commonOtp.setEmail(email);
        commonOtp.setOtp(otp);
        commonOtp.setStatus(OtpStatus.CONFIRMED);

        when(commonOtpRepository.findByEmail(email)).thenReturn(Optional.of(commonOtp));

        authenticationService.verify(email, otp);

        assertEquals(OtpStatus.CONFIRMED, commonOtp.getStatus());
        
        // verify(commonOtpRepository, times(1)).save(commonOtp);
    }
}
