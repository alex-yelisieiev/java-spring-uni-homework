package com.yelisieiev.aspect;

import com.yelisieiev.model.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeAspect {

    /**
     * This aspect intercepts the save method in EmployeeRepository
     * and modifies the position field before saving to the database:
     * - If position is null or empty, sets it to "Стажер" (Intern)
     * - Otherwise, adds a prefix to the existing position
     */
    @Before("execution(* com.yelisieiev.repository.EmployeeRepository.save(..)) && args(employee)")
    public void modifyPositionBeforeSave(JoinPoint joinPoint, Employee employee) {
        if (employee != null) {
            if (employee.getPosition() == null || employee.getPosition().trim().isEmpty()) {
                // Set default position for new employees without position
                employee.setPosition("Стажер");
                System.out.println("AOP: Set default position 'Стажер' for employee: " + 
                        employee.getFirstName() + " " + employee.getLastName());
            } else {
                // Add a prefix to the existing position field
                String modifiedPosition = "[AOP Modified] " + employee.getPosition();
                employee.setPosition(modifiedPosition);
                
                System.out.println("AOP: Modified position field for employee: " + employee.getFirstName() + 
                        " " + employee.getLastName() + ". New position: " + modifiedPosition);
            }
        }
    }
}