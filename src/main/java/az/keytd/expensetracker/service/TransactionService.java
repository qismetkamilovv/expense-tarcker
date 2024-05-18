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
import java.io.FileOutputStream;
import java.io.IOException;
import az.keytd.expensetracker.entities.Transaction;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.TransactionRepository;
import java.io.IOException;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    public Transaction findById(Long accountId) {
        Transaction transaction = transactionRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("account doesn't exist"));
        return transaction;
    }

    public List<Transaction> getAllByAccountId(Long accountId) {
        List<Transaction> transaction = transactionRepository.findAllByAccountId(accountId) ;
        if(transaction.isEmpty()){
            throw new NotFoundException(accountId + "is doesnt exist");
        }
        return transaction;
    }

    public void addExpense(Long accountId, Double amount) {
        Transaction transaction = findById(accountId);
        Double currentAmount = transaction.getAmount();
        Double newAmount = currentAmount - amount;
        transaction.setAmount(newAmount);
        transactionRepository.save(transaction);

    }

    public void addIncome(Long accountId, Double amount) {
        Transaction transaction = findById(accountId);
        Double currentAmount = transaction.getAmount();
        Double newAmount = currentAmount + amount;
        transaction.setAmount(newAmount);
        transactionRepository.save(transaction);
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
            row.createCell(3).setCellValue(transaction.getCategoryId());
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


    // TODO create method that returns list of transactions beetwen dates ;
    // TODO method name is getBetween(LocalDate from, LocalDate to);
    // TODO second method returns excel file;
}
