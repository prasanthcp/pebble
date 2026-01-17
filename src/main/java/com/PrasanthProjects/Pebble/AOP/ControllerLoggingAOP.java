package com.PrasanthProjects.Pebble.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j /** Adds private static final org.slf4j.Logger log
            = LoggerFactory.getLogger(YourClass.class); */
public class ControllerLoggingAOP {

    @Pointcut("within(com.PrasanthProjects.Pebble.Controller.*)")
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
