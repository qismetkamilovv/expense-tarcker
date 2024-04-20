package az.keytd.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Transaction;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getByAllAccountId() {
        return transactionRepository.getAllByAccountId();
    }

    public Transaction findById(Long accountId) {
        Transaction transaction = transactionRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("account doesn't exist"));
        return transaction;
    }

    public Transaction findbyAccountId(Long accountId) {
        Transaction transaction = transactionRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NotFoundException(accountId + "is doesnt exist"));
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
}
