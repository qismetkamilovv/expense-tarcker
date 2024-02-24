package az.keytd.expensetracker.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class OTPService {
    private static final String OTP_CHARS = "0123456789";
    private static final int OTP_LENGTH = 6;

    @Autowired
    private MailSenderService mailSenderService;

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
        String text = "your code: " + otp;
        mailMessage.setText(text);

        mailSenderService.sendMail(to, text);
    }
}
