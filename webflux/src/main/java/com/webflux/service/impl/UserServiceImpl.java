package com.webflux.service.impl;

import com.webflux.model.User;
import com.webflux.repository.UserRepository;
import com.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Mono<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> updateUser(String id, User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    return userRepository.save(existingUser);
                });
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}