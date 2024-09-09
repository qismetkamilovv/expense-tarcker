package az.keytd.expensetracker.dto;

import az.keytd.expensetracker.entities.Role;

public class RegisterRequest {
    private String firstName;

    private String lastName;

    private String email;

    private Role role ;
    
    private String password;


    public String getFirstName() {
        return firstName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
