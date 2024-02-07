package az.keytd.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.entities.Accounts;
import az.keytd.expensetracker.service.AccountsService;

@RestController
@RequestMapping("Accounts")
public class AccountsController {
    @Autowired
    private AccountsService accountsService ;

    @GetMapping("get/UserId")
    public List<Accounts> getByAllUserId(String userId){
       return accountsService.getByAllUserId(userId);
    }
}
