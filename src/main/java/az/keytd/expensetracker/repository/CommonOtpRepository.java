package az.keytd.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.CommonOtp;
import az.keytd.expensetracker.entities.OtpStatus;

@Repository
public interface CommonOtpRepository extends JpaRepository<CommonOtp, Long> {

    Optional<CommonOtp> findByEmail(String email);

    Optional<CommonOtp> findFirstByEmailAndStatus(String email, OtpStatus status );
}
