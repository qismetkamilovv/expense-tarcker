package az.keytd.expensetracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.entities.Transaction;
import az.keytd.expensetracker.service.TransactionService;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("all/account")
    public List<Transaction> getAllByAccountId() {
        return transactionService.getByAllAccountId();
    }

    //TODO: it would be better if you get ID from path variable like account/{id}
    @GetMapping("accountId")
    public Optional<Transaction> findbyAccountId(@RequestParam Long accountId) {
        return transactionService.findbyAccountId(accountId);
    }

    //TODO: it would be better if you get ID from path variable and amount from RequestParama like income/{id}?amount=1.2
    
    @PostMapping("income")
    public ResponseEntity<Transaction> increaseBalance(@PathVariable Long accountId, @RequestParam Double Amount) { // variable name should start with lower case
        transactionService.addIncome(accountId, Amount);
        return ResponseEntity.ok().build();
    }

    // same as above mapping
    @PostMapping("expense")
    public ResponseEntity<Transaction> decreaseBalance(@PathVariable Long accountId, @RequestParam Double Amount) {
        transactionService.addExpense(accountId, Amount);
        return ResponseEntity.ok().build();
    }
}
