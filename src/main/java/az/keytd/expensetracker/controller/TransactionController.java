package az.keytd.expensetracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // this endpoint is not good because one user can see other users' transactions
    // instead do it like "{accountId}/all"
    @GetMapping("all")
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }

    // TODO: why return Optional?
    @GetMapping("accountId")
    public Optional<Transaction> findbyAccountId(@RequestParam Long accountId) {
        return transactionService.findbyAccountId(accountId);
    }
    
    // TODO: it should be POST mapping
    // and you should get "accountId" in path variables, like "{accountId}/income".
    // the following way will work but it is NOT good practice
    // rename to addIncome
    // same for "expense"
    @PutMapping("income")
    public ResponseEntity<Transaction> increaseBalance(@RequestParam Long accountId, @RequestParam Double Amount) {
        transactionService.increaseBalance(accountId, Amount);
        return ResponseEntity.ok().build();
    }

    // TODO: it should be POST mapping
    // rename to addExpense
    @PutMapping("expense")
    public ResponseEntity<Transaction> decreaseBalance(@RequestParam Long accountId, @RequestParam Double Amount) {
        transactionService.decreaseBalance(accountId, Amount);
        return ResponseEntity.ok().build();
    }
}
