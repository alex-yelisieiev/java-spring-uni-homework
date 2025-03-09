package com.yelisieiev.repository;

import com.yelisieiev.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    
    /**
     * Save a user to the database
     * @param user the user to save
     * @return the saved user with generated ID
     */
    User save(User user);
    
    /**
     * Find all users in the database
     * @return list of all users
     */
    List<User> findAll();
    
    /**
     * Find a user by ID
     * @param id the user ID
     * @return an Optional containing the user if found
     */
    Optional<User> findById(Long id);
    
    /**
     * Find a user by username
     * @param username the username to search for
     * @return an Optional containing the user if found
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Update an existing user
     * @param user the user with updated fields
     * @return the updated user
     */
    User update(User user);
    
    /**
     * Delete a user by ID
     * @param id the user ID to delete
     * @return true if deleted successfully, false otherwise
     */
    boolean deleteById(Long id);
}