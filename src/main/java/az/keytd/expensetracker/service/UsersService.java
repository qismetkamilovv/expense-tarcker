package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import az.keytd.expensetracker.dto.CreateUser;
import az.keytd.expensetracker.entities.Users;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.UsersRepository;

import az.keytd.expensetracker.repository.UserRepository;

@Service

public class UsersService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {

        return userRepository.findByEmail(firstName);

    };


public class UsersService {
    @Autowired
    private UsersRepository userRepository ;

    public List<Users> findByAllAddress(String address){
        return userRepository.findAllByAddress(address) ;
    }

    public Users updateData(Long id, CreateUser us){
        Optional<Users> existingData = userRepository.findById(id);
        
        if(existingData.isPresent()){

            Users data = existingData.get();
        
            if(StringUtils.hasText(us.getFirstName())){
                data.setFirstName(us.getFirstName());
            }
            if (StringUtils.hasText(us.getLastName())) {
                data.setLastName(us.getLastName());
            }
            if(StringUtils.hasText(us.getEmail())){
                data.setEmail(us.getEmail());
            }
            if(StringUtils.hasText(us.getPassword())){
                data.setPassword(us.getPassword());
            }
            return userRepository.save(data);
        }
        else{
            throw new NotFoundException("Data with ID " + id + " not found");
        }
        
    }

    public void save(CreateUser newUser){
        Users user = new Users();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

}
