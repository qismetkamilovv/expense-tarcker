package az.keytd.expensetracker.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.CommonOtp;
import az.keytd.expensetracker.entities.OtpStatus;
import az.keytd.expensetracker.repository.CommonOtpRepository;

@Service
public class CommonOtpService {
    private static final String OTP_CHARS = "0123456789";
    private static final int OTP_LENGTH = 6;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private CommonOtpRepository commonOtpsRepository;

    public String generateOtp() {
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(OTP_CHARS.charAt(random.nextInt(OTP_CHARS.length())));
        }
        return otp.toString();
    }

    public void sendByEmail(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String otp = generateOtp();
        mailMessage.setTo(to);
        String text = "your code: " + otp;
        mailMessage.setText(text);
        save(to, otp);
        mailSenderService.sendMail(to, text);
    }

    public void save(String to, String otp) {
        CommonOtp commonotp = new CommonOtp();
        commonotp.setOtp(otp);
        commonotp.setEmail(to);
        commonotp.setRetryCount(1);
        commonotp.setStatus(OtpStatus.NEW);
        commonotp.setCreatedAt(LocalDateTime.now());
        commonOtpsRepository.save(commonotp);
    }

}
