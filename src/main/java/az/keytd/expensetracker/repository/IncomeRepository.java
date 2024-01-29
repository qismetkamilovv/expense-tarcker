package az.keytd.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import az.keytd.expensetracker.entities.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long>{
    // List <Income> getAll();
}
