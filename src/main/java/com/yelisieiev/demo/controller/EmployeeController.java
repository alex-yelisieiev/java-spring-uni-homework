package com.yelisieiev.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @PostMapping
    public ResponseEntity<?> addEmployee() {
        // TODO: Implement adding new employee
        return ResponseEntity.ok("Add employee stub");
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        // TODO: Implement viewing list of employees
        return ResponseEntity.ok("Get all employees stub");
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId) {
        // TODO: Implement updating employee data
        return ResponseEntity.ok("Update employee stub");
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        // TODO: Implement employee deletion
        return ResponseEntity.ok("Delete employee stub");
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable Long employeeId) {
        // TODO: Implement get employee details
        return ResponseEntity.ok("Get employee details stub");
    }
}