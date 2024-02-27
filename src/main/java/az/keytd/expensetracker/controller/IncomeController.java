package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import az.keytd.expensetracker.entities.Income;
import az.keytd.expensetracker.service.IncomeService;

@RestController
@RequestMapping("income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @GetMapping("all")
    public List<Income> getAll(){
        return incomeService.getAll();
    }

    @PutMapping ("income/add")
    public ResponseEntity<Income> income(Long userId, Double amount ){
        incomeService.income(userId, amount);
        return ResponseEntity.ok().build();
    }
}
