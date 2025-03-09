package com.yelisieiev.controller;

import com.yelisieiev.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    private List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("/employees/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/add";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee, 
                             BindingResult result) {
        if (result.hasErrors()) {
            return "employees/add";
        }
        
        employee.setId(nextId++);
        employees.add(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "employees/edit";
        }
        return "redirect:/employees";
    }

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id, 
                               @Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "employees/edit";
        }
        
        Employee existingEmployee = findEmployeeById(id);
        if (existingEmployee != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPosition(employee.getPosition());
            existingEmployee.setSalary(employee.getSalary());
        }
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employees.remove(employee);
        }
        return "redirect:/employees";
    }

    @GetMapping("/employees/view/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "employees/view";
        }
        return "redirect:/employees";
    }

    private Employee findEmployeeById(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}