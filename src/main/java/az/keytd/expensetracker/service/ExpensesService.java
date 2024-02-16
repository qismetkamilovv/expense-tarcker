package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Expenses;
import az.keytd.expensetracker.repository.ExpensesRepository;

@Service
public class ExpensesService {
    @Autowired
    private ExpensesRepository expensesRepository;

    public Expenses findByAccountId(long accountId){
        return expensesRepository.findByAccountId(accountId);
    }

    // add service to subtract amount and also decrease account balance
}
