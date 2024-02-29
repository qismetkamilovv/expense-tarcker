package az.keytd.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
   Optional <Expense> findByAccountId(long accountId);
}
