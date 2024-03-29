package az.keytd.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.entities.Account;
import az.keytd.expensetracker.service.AccountService;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountsService ;

    @GetMapping("UserId")
    public List<Account> findAllByUserId(Long userId){
       return accountsService.findAllByUserId(userId);
    }

    @PutMapping("increaseBalance")
    public ResponseEntity<Account> increaseBalance(@RequestParam Long id, @RequestParam Double balance){
            accountsService.increaseBalance(id, balance);
            return ResponseEntity.ok().build();
    }

    @PutMapping("decraseBalance")
    public ResponseEntity<Account> decraseBalance(@RequestParam Long id, @RequestParam Double balance){
       accountsService.decraseBalance(id, balance);
        return ResponseEntity.ok().build();
    }
}
