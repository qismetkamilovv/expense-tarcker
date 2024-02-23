package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.dto.CreateUser;
import az.keytd.expensetracker.entities.User;
import az.keytd.expensetracker.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService usersService;

    @GetMapping("allAddress")
    public List<User> findAllByAddress(String address) {
        return usersService.findByAllAddress(address);
    }

    @GetMapping("email")
    public Optional<User> findByEmail(String email) {
        return usersService.findByEmail(email);
    }

    @PutMapping("user/name")
    public ResponseEntity<User> updateData(@PathVariable long id, @RequestBody CreateUser us) {
        User updatedData = usersService.updateData(id, us);
        return ResponseEntity.ok(updatedData);
    }

}
