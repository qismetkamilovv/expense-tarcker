package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.service.MailSenderService;

// Unnecessary controller, remove it
@RestController
@RequestMapping("mail")
public class MailSenderController {
    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping("mail/send")
    public ResponseEntity sendMail(@RequestParam String to, @RequestParam String text){
        mailSenderService.sendMail(to, text);
        return ResponseEntity.ok().build();
    }

    @PostMapping("mail/save")
    public ResponseEntity save (String to, String text){
        mailSenderService.save(to, text);
        return ResponseEntity.ok().build();
    }
}
