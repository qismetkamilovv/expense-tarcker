package az.keytd.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

    List<Account> findAllByUserId(Long userId);

    
}
