package az.keytd.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.CommonOtps;

@Repository
public interface CommonOtpsRepository extends JpaRepository<CommonOtps, Long> {
    
}
