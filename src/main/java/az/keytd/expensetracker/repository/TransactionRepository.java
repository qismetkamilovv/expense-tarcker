package az.keytd.expensetracker.repository;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Transaction;
import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccountId(Long accountId);

    List<Transaction> findAllByTransactionDateBetween(LocalDateTime from, LocalDateTime to);

    @Transactional
    @Modifying
    @Query("UPDATE Transaction t SET t.deleteAt WHERE t.id = :id")
    void softDelete(Long id, LocalDateTime deleteAt);

    @Transactional
    @Modifying
    @Query
    void restore(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.deleteAt IS NULL")
    List<Transaction>findAllActive();
}