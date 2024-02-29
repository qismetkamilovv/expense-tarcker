package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Expense;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.ExpenseRepository;

@Service
public class ExpensesService {
    @Autowired
    private ExpenseRepository expensesRepository;

    public Expense findByAccountId(Long accountId) {
        return expensesRepository.findByAccountId(accountId);
    }

    public void expense(Long accountId, Double amount) {
        Expense expense = expensesRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(accountId + " not found"));

        Double currentAmount = expense.getAmount();
        Double newAmount = currentAmount - amount;
        expense.setAmount(newAmount);
        expensesRepository.save(expense);
    }
}
