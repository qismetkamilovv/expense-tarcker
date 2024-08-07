package az.keytd.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import az.keytd.expensetracker.dto.CreateUser;
import az.keytd.expensetracker.dto.RegisterRequest;
import az.keytd.expensetracker.entities.User;
import az.keytd.expensetracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Controller")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("byEmail")
    public User getByEmail(@RequestParam String email){
        return userService.getByEmail(email);
    }

    @Operation(summary = "Update user")
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody CreateUser us) {
        User updatedData = userService.updateUser(id, us);
        return ResponseEntity.ok(updatedData);
    }

}