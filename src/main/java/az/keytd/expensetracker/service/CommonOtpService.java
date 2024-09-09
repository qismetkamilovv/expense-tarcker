package az.keytd.expensetracker.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.CommonOtp;
import az.keytd.expensetracker.entities.OtpStatus;
import az.keytd.expensetracker.exceptions.BadRequestException;
import az.keytd.expensetracker.exceptions.NotFoundException;
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

    public void checkOTP(String email, String otp) {
        CommonOtp commonOtp = commonOtpsRepository.findFirstByEmailAndStatus(email, OtpStatus.NEW)
                .orElseThrow(() -> new NotFoundException(email + " not found"));

        if (!commonOtp.getOtp().equals(otp)) {
            int count = commonOtp.getRetryCount() + 1;
            if (count > 3) {
                commonOtp.setStatus(OtpStatus.FAILED);
                commonOtpsRepository.save(commonOtp);
                throw new BadRequestException("your otp expired");
                
            }
            commonOtp.setRetryCount(count);
            commonOtpsRepository.save(commonOtp);
            throw new BadRequestException("your otp code is not true");
        }
        commonOtp.setStatus(OtpStatus.OK);
        commonOtpsRepository.save(commonOtp);

    }

}
