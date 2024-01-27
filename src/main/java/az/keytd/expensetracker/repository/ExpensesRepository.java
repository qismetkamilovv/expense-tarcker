package az.keytd.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Expenses;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
    
}
