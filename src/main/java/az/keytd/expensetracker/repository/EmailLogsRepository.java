package az.keytd.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.EmailLogs;

@Repository
public interface EmailLogsRepository extends JpaRepository<EmailLogs, Long> {
    
}
