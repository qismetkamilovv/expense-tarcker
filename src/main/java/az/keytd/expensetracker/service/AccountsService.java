package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Accounts;
import az.keytd.expensetracker.repository.AccountsRepository;

@Service
public class AccountsService {
    @Autowired
    private AccountsRepository accountsRepository;

    public Accounts findByName(String name) {

        return accountsRepository.findByName(name);

    }
}
