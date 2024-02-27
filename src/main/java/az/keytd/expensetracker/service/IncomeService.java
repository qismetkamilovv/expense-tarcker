package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import az.keytd.expensetracker.entities.Income;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.IncomeRepository;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private AccountService accountService;

    public List <Income> getAll(){
       return  incomeRepository.findAll();
    }

    public void income(Long userId, Double amount ){
        Income income = incomeRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(userId + " not found"));
            
        Double currentAmount = income.getAmount();
        // Double newAmount = accountService.increaseBalance(userId, amount); type mismatch
        Double newAmount = currentAmount + amount ;
        income.setAmount(newAmount);
        incomeRepository.save(income);
    }
    // service for add income
}
