package az.keytd.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long>{
    
}
