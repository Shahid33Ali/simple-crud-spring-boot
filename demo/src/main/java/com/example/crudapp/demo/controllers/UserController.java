package com.example.crudapp.demo.controllers;

import com.example.crudapp.demo.models.User;
import com.example.crudapp.demo.serivces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User newUser=userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @GetMapping("")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users= userService.getUsers();
        return ResponseEntity.ok(users);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody User user){
        User updatedUser=userService.updateUser(id,user);
        return ResponseEntity.ok(updatedUser);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User foundUser=userService.getUserById(id);
        return ResponseEntity.ok(foundUser);
    }

}
