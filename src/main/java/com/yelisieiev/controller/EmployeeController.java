package com.yelisieiev.controller;

import com.yelisieiev.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        // TODO: Implement add employee logic
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        // TODO: Implement get all employees logic
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        // TODO: Implement update employee logic
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        // TODO: Implement delete employee logic
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeDetails(@PathVariable Long employeeId) {
        // TODO: Implement get employee details logic
        return ResponseEntity.ok(new Employee());
    }
}