package az.keytd.expensetracker.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OTPService {
    private static final String OTP_CHARS = "0123456789";
    private static final int OTP_LENGTH = 6;
    // TODO javameilsender instead of ypur mailsender service
    @Autowired
    private JavaMailSender javaMailSender;

    public static String generateOtp() {
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(OTP_CHARS.charAt(random.nextInt(OTP_CHARS.length())));
        }
        return otp.toString();
    }

    public void sendByEmail(String to, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        // TODO create text veriable string type= "your code: " + otp
        mailMessage.setText("your OTP" + otp);

        javaMailSender.send(mailMessage);
    }
}
