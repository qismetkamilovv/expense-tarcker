package az.keytd.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Users;
import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<Users,Long>  {
    
    Optional<Users>findByFirstName(String firstName) ;

    // TODO return as Optional
    Users findByEmail(String email);

    List<Users> findAllByAddress (String address);

    
}
