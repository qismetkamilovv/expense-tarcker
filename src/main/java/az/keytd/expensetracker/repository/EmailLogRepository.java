package az.keytd.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
    
}
