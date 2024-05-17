package az.keytd.expensetracker.controller;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("{accountId}")
    public List<Transaction> getAllByAccountId(@PathVariable Long accountId) {
        return transactionService.getAllByAccountId(accountId);
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

    @GetMapping("between-dates")
    public List<Transaction> getBeetwen(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {

        return transactionService.getBetween(from, to);

    }

    @GetMapping("/transactions/export")
    public void getBeetwen(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam("filePath") String filePath) {
                // endpointden excel file return ele.
                
    }
}
