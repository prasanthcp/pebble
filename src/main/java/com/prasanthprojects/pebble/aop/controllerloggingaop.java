package com.prasanthprojects.pebble.aop;

//import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Slf4j
public class controllerloggingaop {
    /** Adds private static final org.slf4j.Logger log = LoggerFactory.getLogger(YourClass.class); */

    private static org.slf4j.Logger log = LoggerFactory.getLogger(controllerloggingaop.class);

    controllerloggingaop() {
        log = LoggerFactory.getLogger(controllerloggingaop.class);
    }

    @Pointcut("within(com.prasanthprojects.pebble.controller.*)")
    public void ControllerMethods() {}

    @Before("ControllerMethods()")
    public void BeforeController(JoinPoint joinPoint) {
        log.info("Entering controller {} with arg {}", joinPoint.getSignature(),joinPoint.getArgs());
    }

    @AfterReturning(pointcut="ControllerMethods()", returning = "result")
    public void logAfterController(JoinPoint joinPoint, Object result) {
        log.info("Exiting controller {} with result {}", joinPoint.getSignature(),result);
    }

}
