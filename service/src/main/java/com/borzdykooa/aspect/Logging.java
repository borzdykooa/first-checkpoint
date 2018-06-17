package com.borzdykooa.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {

    @Pointcut("execution(public * com.borzdykooa.service.*.*(..))")
    public void addLogging() {
    }

    @After("addLogging()")
    public void afterLogging() {
        System.out.println("logging");
    }
}
