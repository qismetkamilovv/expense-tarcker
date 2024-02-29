package az.keytd.expensetracker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.entities.Expense;
import az.keytd.expensetracker.service.ExpensesService;

@RestController
@RequestMapping("Expenses")
public class ExpenseController {
    @Autowired
    private ExpensesService expensesService;

    @GetMapping("by-accountId")
    public Optional<Expense> findByAccountId(long accountId){
        return expensesService.findByAccountId(accountId);
    }
}
