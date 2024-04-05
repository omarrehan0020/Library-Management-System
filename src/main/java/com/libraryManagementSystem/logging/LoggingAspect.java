package com.libraryManagementSystem.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.libraryManagementSystem.controllers..*(..))")
    private void controllerMethods() {}

    @Pointcut("execution(* com.libraryManagementSystem.services..*(..))")
    private void serviceMethods() {}

    @Before("controllerMethods() || serviceMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} is called with arguments {}", methodName, joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "controllerMethods() || serviceMethods()", returning = "returnValue")
    public void logMethodExit(JoinPoint joinPoint, Object returnValue) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} returned {}", methodName, returnValue);
    }

    @AfterThrowing(pointcut = "controllerMethods() || serviceMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception in method {}: {}", methodName, exception.getMessage());
    }
}
