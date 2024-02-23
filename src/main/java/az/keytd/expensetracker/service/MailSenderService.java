package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired 
    private JavaMailSender javamailSender ;

    public String sendMail(String from, String to, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("qismet600@gmail.com");
        message.setTo("kamilovvqismet@gmail.com");
        message.setText("SALAAAAAAAAM");
        javamailSender.send(message);
        return "sended";
    }
}
