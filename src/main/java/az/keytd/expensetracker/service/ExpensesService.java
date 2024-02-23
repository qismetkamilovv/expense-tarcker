package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Expense;
import az.keytd.expensetracker.repository.ExpenseRepository;

@Service
public class ExpensesService {
    @Autowired
    private ExpenseRepository expensesRepository;

    @Autowired
    private AccountService accountService;

    public Expense findByAccountId(Long accountId){
        return expensesRepository.findByAccountId(accountId);
    }


    // add service to subtract amount and also decrease account balance
}
