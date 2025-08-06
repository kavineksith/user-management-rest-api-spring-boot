package com.example.demo.Domain.service;

import com.example.demo.Application.dto.request.UpdateUser;
import com.example.demo.Application.dto.request.UserRegister;
import com.example.demo.Application.dto.response.AllUsersDetails;
import com.example.demo.Application.dto.response.UserDetails;
import com.example.demo.Domain.model.User;
import com.example.demo.External.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Define methods for user operations here
    public ResponseEntity<User> createUser(UserRegister userRegister) {
        // Check if the user with the given username already exists
        Optional<User> optionalUser = userRepository.findByUsername(userRegister.getUsername());

        // Check if the user already exists
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(409).build(); // Conflict status if user already exists
        } else {
            User user = new User();
            user.setUsername(userRegister.getUsername());
            user.setName(userRegister.getName());
            user.setPassword(userRegister.getPassword());
            // Save the user to the repository
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        }
    }

    public ResponseEntity<UserDetails> getUserDetails(String username) {
        UserDetails userDetails = new UserDetails();
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userDetails.setId(user.getId());
            userDetails.setUsername((user.getUsername()));
            userDetails.setName(user.getName());
            userDetails.setPassword(user.getPassword());
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> updateUserDetails(String username, UpdateUser updateUser) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(updateUser.getUsername());
            user.setName(updateUser.getName());
            user.setPassword(updateUser.getPassword());
            // Save the updated user to the repository
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }
    }

    @Transactional
    public ResponseEntity<String> deleteUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            userRepository.deleteByUsername(username);
            return ResponseEntity.ok(username + " is delete successfully!");
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }
    }

    public ResponseEntity<Iterable<AllUsersDetails>> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        Iterable<AllUsersDetails> allUsersDetails = convertToAllUsersDetails(users);

        if (!allUsersDetails.iterator().hasNext()) {
            return ResponseEntity.noContent().build(); // Return 204 if no users found
        } else {
            return ResponseEntity.ok(allUsersDetails);
        }
    }

    public Iterable<AllUsersDetails> convertToAllUsersDetails(Iterable<User> users) {
        List<AllUsersDetails> allUsersDetailsList = new ArrayList<>();

        for (User user: users) {
            AllUsersDetails allUsersDetails = new AllUsersDetails();
            allUsersDetails.setUsername(user.getUsername());
            allUsersDetails.setName(user.getName());
            allUsersDetails.setPassword(user.getPassword());
            allUsersDetailsList.add(allUsersDetails);
        }

        return allUsersDetailsList;
    }
}
