package com.yelisieiev.project.controller;

import com.yelisieiev.project.model.Employee;
import com.yelisieiev.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@Controller
public class WebController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public WebController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
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
        
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    model.addAttribute("employee", employee);
                    return "employees/edit";
                })
                .orElse("redirect:/employees");
    }

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id, 
                               @Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "employees/edit";
        }
        
        employee.setId(id);
        employeeRepository.update(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/view/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    model.addAttribute("employee", employee);
                    return "employees/view";
                })
                .orElse("redirect:/employees");
    }
}