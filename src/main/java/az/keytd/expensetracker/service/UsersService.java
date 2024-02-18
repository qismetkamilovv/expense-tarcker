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
import az.keytd.expensetracker.entities.Users;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.UsersRepository;

@Service

public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {

        return userRepository.findByEmail(firstName);

    }

    public List<Users> findByAllAddress(String address) {
        return userRepository.findAllByAddress(address);
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users updateData(Long id, CreateUser us) {
        Users user = userRepository.findById(id)
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

    public Users save(RegisterRequest newUser) {
        Users user = new Users();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(newUser.getRole());
        return userRepository.save(user);
    }

}
