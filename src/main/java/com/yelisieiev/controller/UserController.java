package com.yelisieiev.controller;

import com.yelisieiev.model.User;
import com.yelisieiev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        return userRepository.findByUsername(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .map(u -> ResponseEntity.ok("Login successful"))
                .orElse(ResponseEntity.badRequest().body("Invalid credentials"));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        User updatedUser = userRepository.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}