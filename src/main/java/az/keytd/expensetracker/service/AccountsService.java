package az.keytd.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Accounts;
import az.keytd.expensetracker.repository.AccountsRepository;

@Service
public class AccountsService {
    @Autowired
    private AccountsRepository accountsRepository;

    public List<Accounts> getByAllUserId(String userId) {

        return accountsRepository.getByAllUserId(userId);
    }

}
