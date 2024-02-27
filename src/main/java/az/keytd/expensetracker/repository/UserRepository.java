package az.keytd.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long>  {
    
    Optional<User>findByFirstName(String firstName) ;

    // TODO return as Optional
    Optional <User> findByEmail(String email);

    List<User> findAllByAddress (String address);

    
}
