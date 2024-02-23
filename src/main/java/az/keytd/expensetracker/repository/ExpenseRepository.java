package az.keytd.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    Expense findByAccountId(long accountId);
}
