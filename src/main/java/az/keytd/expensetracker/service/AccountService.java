package az.keytd.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Account;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAllByUserId(Long userId) {

        return accountRepository.findAllByUserId(userId);
    }

    public void increaseBalance(Long id, Double balance) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data with ID " + id + " not found"));

        Double currentBalance = account.getBalance();
        Double newBalance = currentBalance + balance;

     account.setBalance(newBalance);
        accountRepository.save (account);
    }

    public void decraseBalance(Long id, Double balance) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data with ID " + id + " not found"));

        Double currentBalance = account.getBalance();
        Double newBalance = currentBalance + balance;

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

}
