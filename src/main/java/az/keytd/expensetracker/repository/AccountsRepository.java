package az.keytd.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long>{
    // TODO rename to findAllByUserId and userId is Long not String
    List<Accounts> getByAllUserId(String userId);
}
