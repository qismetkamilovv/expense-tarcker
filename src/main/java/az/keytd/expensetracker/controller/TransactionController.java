package az.keytd.expensetracker.controller;

import java.util.List;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.dto.TransactionRequest;
import az.keytd.expensetracker.entities.Transaction;
import az.keytd.expensetracker.service.TransactionService;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("{id}")
    public List<Transaction> getAllByAccountId(@PathVariable Long id) {
        return transactionService.getAllByAccountId(id);
    }

    @GetMapping
    public List<Transaction> findAllActive(){
        return transactionService.findAllActive();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> softDeleteTransaction(@PathVariable Long id){
        transactionService.softDeleteTransaction(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/restore")
    public ResponseEntity<Void> restore(@PathVariable Long id){
        transactionService.restore(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{accountId}/income")
    public ResponseEntity<Transaction> addIncome(@PathVariable Long accountId,
            @RequestBody TransactionRequest request) {
        Transaction tt =transactionService.addIncome(accountId, request);
        return ResponseEntity.ok(tt);
    }

    @PostMapping("expense")
    public ResponseEntity<Transaction> addExpense(@PathVariable Long accountId,
            @RequestBody TransactionRequest request) {
        Transaction tt =transactionService.addExpense(accountId, request);
        return ResponseEntity.ok(tt);
    }

    @GetMapping("between-dates")
    public List<Transaction> getBeetwen(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {

        return transactionService.getBetween(from, to);

    }

    @GetMapping("/transactions/export")
    public ResponseEntity<byte[]> exportTransactionsToExcel(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        try {
            byte[] excelBytes = transactionService.getBetweenReportExcel(from, to);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "transactions.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
