package com.example.demo.Application.controller;

import com.example.demo.Application.dto.request.UpdateUser;
import com.example.demo.Application.dto.request.UserRegister;
import com.example.demo.Application.dto.response.AllUsersDetails;
import com.example.demo.Application.dto.response.UserDetails;
import com.example.demo.Domain.model.User;
import com.example.demo.Domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Define endpoints for user operations here
    @GetMapping("/")
    public String hello() {
        return "Hello, User!";
    }


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRegister userRegister) {
        return userService.createUser(userRegister);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable String username) {
        return userService.getUserDetails(username);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser(@Valid @PathVariable String username,  @RequestBody UpdateUser updateUser) {
        return userService.updateUserDetails(username, updateUser);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<AllUsersDetails>> showAllUsers() {
        return userService.getAllUsers();
    }
}
