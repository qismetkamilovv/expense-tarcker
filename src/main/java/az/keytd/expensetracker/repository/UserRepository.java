package az.keytd.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>  {
    
    Optional<Users>findByFirstName(String firstName) ;

    Users findByEmail(String email);
}
