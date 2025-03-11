package com.yelisieiev.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerLoggingAspect {

    /**
     * This aspect logs the arguments and return value after executing methods in UserController
     */
    @AfterReturning(pointcut = "execution(* com.yelisieiev.controller.UserController.*(..))", returning = "result")
    public void logAfterUserControllerMethodExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n=== AOP Logging ===\n");
        logMessage.append("Controller: ").append(className).append("\n");
        logMessage.append("Method: ").append(methodName).append("\n");
        logMessage.append("Arguments: ").append(Arrays.toString(args)).append("\n");
        logMessage.append("Return value: ").append(result).append("\n");
        logMessage.append("==================\n");
        
        System.out.println(logMessage.toString());
    }
    
    /**
     * This aspect logs the arguments after executing methods in EmployeeController
     */
    @AfterReturning(pointcut = "execution(* com.yelisieiev.controller.EmployeeController.*(..))", returning = "result")
    public void logAfterEmployeeControllerMethodExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n=== Employee Controller AOP Logging ===\n");
        logMessage.append("Controller: ").append(className).append("\n");
        logMessage.append("Method: ").append(methodName).append("\n");
        logMessage.append("Arguments: ").append(Arrays.toString(args)).append("\n");
        logMessage.append("==================\n");
        
        System.out.println(logMessage.toString());
    }
}