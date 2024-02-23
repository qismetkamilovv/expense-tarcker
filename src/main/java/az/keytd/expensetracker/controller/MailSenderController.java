package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.service.MailSenderService;

@RestController
@RequestMapping("mail")
public class MailSenderController {
    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping("sendmail")
    public String sendMail(@RequestParam String to, @RequestParam String text){
        mailSenderService.sendMail(to, text);
        return "mail sended";
    }
}
