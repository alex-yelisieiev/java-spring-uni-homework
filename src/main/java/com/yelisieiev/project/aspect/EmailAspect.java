package com.yelisieiev.project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yelisieiev.project.service.EmailService;
//9 лаба
@Aspect
@Component
public class EmailAspect {
    private final EmailService emailService;

    @Autowired
    public EmailAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @AfterReturning("execution(* com.yelisieiev.project.repository.EmployeeRepository.save(..))")
    public void sendEmailAboutEmployeeAspect(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String message = "Метод " + methodName + " був виконаний.";
        emailService.sendEmail("alexander0402112@gmail.com", "Сповіщення EmployeeAspect спрацював", message);
    }

    @AfterReturning("execution(* com.yelisieiev.project.controller.WebController.*(..))")
    public void sendEmailAboutControllerLoggingAspect(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String message = "Метод " + methodName + " був виконаний.";
        emailService.sendEmail("alexander0402112@gmail.com", "Сповіщення ControllerLoggingAspect спрацював з EmployeeController", message);
    }
}
