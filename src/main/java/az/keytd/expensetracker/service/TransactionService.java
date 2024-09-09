package az.keytd.expensetracker.service;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import az.keytd.expensetracker.dto.TransactionRequest;
import az.keytd.expensetracker.entities.Account;
import az.keytd.expensetracker.entities.Category;
import az.keytd.expensetracker.entities.Transaction;
import az.keytd.expensetracker.entities.TransactionType;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public void softDeleteTransaction(Long id) {
        transactionRepository.softDelete(id, LocalDateTime.now());
    }

    public void restore(Long id) {
        transactionRepository.restore(id);
    }

    public List<Transaction> findAllActive() {
        return transactionRepository.findAllActive();
    }

    public Transaction findById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("transaction doesn't exist"));
        return transaction;
    }

    public List<Transaction> getAllByAccountId(Long accountId) {
        List<Transaction> transaction = transactionRepository.findAllByAccountId(accountId);
        if (transaction.isEmpty()) {
            throw new NotFoundException(accountId + "is doesnt exist");
        }
        return transaction;
    }

    public Transaction addExpense(Long accountId, TransactionRequest request) {
        Account account = accountService.decraseBalance(accountId, request.getAmount());
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(request.getAmount());
        transaction.setCategory(new Category(request.getCategoryId()));
        transaction.setTitle(request.getTitle());
        transaction.setTransactionDate(request.getTransactionDate());
        transaction.setTrnType(TransactionType.EXPENSE);
        transaction.setCreateAt(LocalDateTime.now());
        transactionRepository.save(transaction);
        return transaction;

    }

    public Transaction addIncome(Long accountId, TransactionRequest request) {
        Account account = accountService.increaseBalance(accountId, request.getAmount());
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(request.getAmount());
        transaction.setCategory(new Category(request.getCategoryId()));
        transaction.setTrnType(TransactionType.INCOME);
        transaction.setCreateAt(LocalDateTime.now());
        transaction.setTitle(request.getTitle());
        transaction.setTransactionDate(request.getTransactionDate());
        transactionRepository.save(transaction);
        return transaction;
    }

    public List<Transaction> getBetween(LocalDate from, LocalDate to) {
        LocalDateTime fromTime = LocalDateTime.of(from, LocalTime.MIDNIGHT);
        LocalDateTime toTime = LocalDateTime.of(to, LocalTime.MIDNIGHT);
        return transactionRepository.findAllByTransactionDateBetween(fromTime, toTime);
    }

    public byte[] getBetweenReportExcel(LocalDate from, LocalDate to) throws IOException {
        List<Transaction> transactions = this.getBetween(from, to);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Transactions");

        String[] headers = { "Transaction ID", "Account ID", "Title", "Amount", "Category ID", "Transaction Date",
                "Transaction Type", "Created At", "Updated At" };
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int rowNum = 1;
        for (Transaction transaction : transactions) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(transaction.getId());
            row.createCell(1).setCellValue(transaction.getTitle());
            row.createCell(2).setCellValue(transaction.getAmount());
            // row.createCell(3).setCellValue(transaction.getCategoryId());
            row.createCell(4).setCellValue(transaction.getTransactionDate().format(formatter));
            row.createCell(5).setCellValue(transaction.getTrnType().toString());
            row.createCell(6).setCellValue(transaction.getCreateAt().format(formatter));
            row.createCell(7).setCellValue(transaction.getUpdateAt().format(formatter));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
