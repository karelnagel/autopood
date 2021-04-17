package com.example.autopood.controllers;

import com.example.autopood.models.User;
import com.example.autopood.repositorities.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User userObj) {
        return userRepository.save(userObj);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(String.valueOf(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User"+ "id"+ userId));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value="id") Long userId){
        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("User"+"id"+userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}