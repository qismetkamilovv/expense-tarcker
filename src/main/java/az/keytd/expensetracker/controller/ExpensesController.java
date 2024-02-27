package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.entities.Expenses;
import az.keytd.expensetracker.service.ExpensesService;

@RestController
@RequestMapping("Expenses")
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;

    @GetMapping("get/accountId")
    public Expenses findByAccountId(long accountId){
        return expensesService.findByAccountId(accountId);
    }
}
