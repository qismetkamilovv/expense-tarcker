package az.keytd.expensetracker.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.EmailLog;
import az.keytd.expensetracker.entities.EmailSendStatus;
import az.keytd.expensetracker.repository.EmailLogRepository;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javamailSender;

    @Autowired
    private EmailLogRepository emailLogsRepository;

    public void sendMail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(text);
        EmailLog emailLog = new EmailLog();
        emailLog.setRecipient(to);
        emailLog.setSendTimesTamp(LocalDateTime.now());
        emailLog.setBody(text);
        emailLog.setStatus(EmailSendStatus.SENT);
        emailLog.setType("OTP");
        emailLogsRepository.save(emailLog);
        javamailSender.send(message);
    }
}
