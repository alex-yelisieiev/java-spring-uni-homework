package com.webflux.controller;

import com.webflux.model.User;
import com.webflux.model.dto.AuthRequest;
import com.webflux.model.dto.AuthResponse;
import com.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<AuthResponse>> register(@RequestBody AuthRequest request) {
        return userService.existsByUsername(request.getUsername())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(new AuthResponse(false, "Username already exists", null)));
                    }
                    
                    return userService.existsByEmail(request.getEmail())
                            .flatMap(emailExists -> {
                                if (Boolean.TRUE.equals(emailExists)) {
                                    return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                                            .body(new AuthResponse(false, "Email already exists", null)));
                                }
                                
                                User user = new User();
                                user.setUsername(request.getUsername());
                                user.setEmail(request.getEmail());
                                user.setPassword(passwordEncoder.encode(request.getPassword()));
                                
                                return userService.createUser(user)
                                        .map(savedUser -> ResponseEntity.status(HttpStatus.CREATED)
                                                .body(new AuthResponse(true, "User registered successfully", savedUser)));
                            });
                });
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest request) {
        return userService.getUserByUsername(request.getUsername())
                .flatMap(user -> {
                    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        return Mono.just(ResponseEntity.ok(new AuthResponse(true, "Login successful", user)));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new AuthResponse(false, "Invalid credentials", null)));
                    }
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthResponse(false, "Invalid credentials", null)));
    }
}