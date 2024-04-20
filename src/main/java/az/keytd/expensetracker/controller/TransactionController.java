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

    @PostMapping("income")
    public ResponseEntity<Transaction> increaseBalance(@PathVariable Long accountId, @RequestParam Double amount) { 
        transactionService.addIncome(accountId, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("expense")
    public ResponseEntity<Transaction> decreaseBalance(@PathVariable Long accountId, @RequestParam Double amount) {
        transactionService.addExpense(accountId, amount);
        return ResponseEntity.ok().build();
    }
}
