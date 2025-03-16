package com.webflux.service;

import com.webflux.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<User> getAllUsers();
    Mono<User> getUserById(String id);
    Mono<User> getUserByUsername(String username);
    Mono<User> getUserByEmail(String email);
    Mono<User> createUser(User user);
    Mono<User> updateUser(String id, User user);
    Mono<Void> deleteUser(String id);
    Mono<Boolean> existsByUsername(String username);
    Mono<Boolean> existsByEmail(String email);
}