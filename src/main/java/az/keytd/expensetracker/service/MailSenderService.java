package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javamailSender;

    public void sendMail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(text);
        javamailSender.send(message);
    }
}
