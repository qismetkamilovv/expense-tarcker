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
import az.keytd.expensetracker.entities.Users;
import az.keytd.expensetracker.service.UsersService;

import java.util.List;

@RestController
@RequestMapping("Users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("get/allAddress")
    public List<Users>findAllByAddress(String address){
        return usersService.findByAllAddress(address);
    }

    @GetMapping("get/email")
    public Users findByEmail(String email){
        return usersService.findByEmail(email);
    }

    @PutMapping("updateUser")
    public ResponseEntity<Users>updateData(@PathVariable long id, @RequestBody CreateUser us){
        Users updatedData = usersService.updateData(id, us);
        return ResponseEntity.ok(updatedData);
    }

    @PostMapping("saveUser")
    public ResponseEntity<String> save(CreateUser newUser){
        usersService.save(newUser);
        return ResponseEntity.ok().build();
    }


}
