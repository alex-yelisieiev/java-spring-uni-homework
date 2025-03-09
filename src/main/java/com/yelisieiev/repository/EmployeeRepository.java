package com.yelisieiev.repository;

import com.yelisieiev.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    
    /**
     * Save an employee to the database
     * @param employee the employee to save
     * @return the saved employee with generated ID
     */
    Employee save(Employee employee);
    
    /**
     * Find all employees in the database
     * @return list of all employees
     */
    List<Employee> findAll();
    
    /**
     * Find an employee by ID
     * @param id the employee ID
     * @return an Optional containing the employee if found
     */
    Optional<Employee> findById(Long id);
    
    /**
     * Update an existing employee
     * @param employee the employee with updated fields
     * @return the updated employee
     */
    Employee update(Employee employee);
    
    /**
     * Delete an employee by ID
     * @param id the employee ID to delete
     * @return true if deleted successfully, false otherwise
     */
    boolean deleteById(Long id);
}