package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/api/user/{email}")
    public ResponseEntity<User> getUser(@PathVariable(name="email") String email){
        try {
            return new ResponseEntity<User>(userService.getUser(email), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<String>("The user was created successfully.", HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<String>("Failed to create user.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/user/{email}")
    public ResponseEntity<String> putUser(@RequestBody User newUser, @PathVariable(name="email") String email){
        try{
            User currentUser = userService.getUser(email);
            if (currentUser != null){
                currentUser.setEmail(newUser.getEmail());
                currentUser.setFirstName(newUser.getFirstName());
                currentUser.setLastName(newUser.getLastName());
                currentUser.setPassword(newUser.getPassword());
                userService.updateUser(currentUser);
            }
            return new ResponseEntity<String>("The user was updated successfully.", HttpStatus.NO_CONTENT);
        }
        catch (Exception exception){
            return new ResponseEntity<String>("Failed to update user.", HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/api/user/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable(name="email") String email){
        try{
            User currentUser = userService.getUser(email);
            if(currentUser != null){
                userService.deleteUser(email);
            }
            return new ResponseEntity<String>("The user was deleted successfully.", HttpStatus.NO_CONTENT);
        }
        catch(Exception exception){
            return new ResponseEntity<String>("Failed to delete user.", HttpStatus.BAD_REQUEST);
        }

    }

}
