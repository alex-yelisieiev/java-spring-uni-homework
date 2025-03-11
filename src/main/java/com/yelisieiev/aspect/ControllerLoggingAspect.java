package com.yelisieiev.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class ControllerLoggingAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(ControllerLoggingAspect.class);

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
        
        logger.info(logMessage.toString());
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
        logMessage.append("Return value: ").append(result).append("\n");
        logMessage.append("==================\n");
        
        logger.info(logMessage.toString());
    }
    
    /**
     * This aspect logs the arguments after executing methods in WebController
     */
    @AfterReturning(pointcut = "execution(* com.yelisieiev.controller.WebController.*(..))", returning = "result")
    public void logAfterWebControllerMethodExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n=== Web Controller AOP Logging ===\n");
        logMessage.append("Controller: ").append(className).append("\n");
        logMessage.append("Method: ").append(methodName).append("\n");
        logMessage.append("Arguments: ").append(Arrays.toString(args)).append("\n");
        logMessage.append("Return value: ").append(result).append("\n");
        logMessage.append("==================\n");
        
        logger.info(logMessage.toString());
    }
}