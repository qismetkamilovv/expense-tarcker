package az.keytd.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import az.keytd.expensetracker.dto.CreateUser;
import az.keytd.expensetracker.dto.RegisterRequest;
import az.keytd.expensetracker.entities.User;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.UserRepository;

@Service

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        return getByEmail(firstName);

    }

    // this method not needed, remove
    public List<User> findByAllAddress(String address) {
        return userRepository.findAllByAddress(address);
    }

    public User getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(email + " not found"));
//you do not need save it you found above just return user;
// or you just can write:
// return  userRepository.findByEmail(email)
// .orElseThrow(() -> new NotFoundException(email + " not found"));
// for simplicity
        return userRepository.save(user);
    }

    //TODO: here don't return optional, handle if user does not exist by email case in orElseThrow
    // and you actually do not need this method you already have one getByEmail()
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // rename to updateUser
    public User updateData(Long id, CreateUser us) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data with ID " + id + " not found"));

        if (StringUtils.hasText(us.getFirstName())) {
            user.setFirstName(us.getFirstName());
        }
        if (StringUtils.hasText(us.getLastName())) {
            user.setLastName(us.getLastName());
        }
        if (StringUtils.hasText(us.getEmail())) {
            user.setEmail(us.getEmail());
        }
        if (StringUtils.hasText(us.getPassword())) {
            user.setPassword(us.getPassword());
        }
        return userRepository.save(user);
    }

    public User save(RegisterRequest newUser, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(newUser.getRole());
        // add status as unconfirmed
        return userRepository.save(user);
    }

}
