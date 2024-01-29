package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.entities.Accounts;
import az.keytd.expensetracker.service.AccountsService;

@RestController
@RequestMapping("auth")
public class AccountsController {
    @Autowired
    private AccountsService accountsService ;

    @GetMapping("get/name")
    public Accounts findByName(String name){
        return accountsService.findByName(name);
    }
}
