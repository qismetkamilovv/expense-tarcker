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
// TODO use lowercase in api names 
@RequestMapping("Users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    // TODO you do NOT need "get" when getting, http METHOD GET is enough and explains that your API is gettting something
    // do NOT use camecase use kebab-case for API names. ex. "by-address"
    @GetMapping("get/allAddress")
    public List<Users> findAllByAddress(String address) {
        return usersService.findByAllAddress(address);
    }

    // again you do NOT need say get/email, no need to use "get" in path because, again HTTP method GET tell client that this API gettting something
    // and you are not getting email but rather get User by email so prefered name would be "by-email"
    @GetMapping("get/email")
    public Users findByEmail(String email) {
        return usersService.findByEmail(email);
    }


    // same as above, you do NOT need to specify it is "udate" HTTP method PUT explains so empty path is enough, like  @PutMapping
    // in case you eed to update specific fieldd, like name, you can same "name" with PUT method, meaning update name of User, like @PutMapping("name")
    // absolote path would be like PUT "user/name"
    @PutMapping("updateUser")
    public ResponseEntity<Users> updateData(@PathVariable long id, @RequestBody CreateUser us) {
        Users updatedData = usersService.updateData(id, us);
        return ResponseEntity.ok(updatedData);
    }

}
