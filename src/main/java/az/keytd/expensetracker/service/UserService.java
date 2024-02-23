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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        return getByEmail(firstName);

    }

    public List<User> findByAllAddress(String address) {
        return userRepository.findAllByAddress(address);
    }

    public User getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(email + " not found"));

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

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

    public User save(RegisterRequest newUser) {
        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(newUser.getRole());
        return userRepository.save(user);
    }

}
