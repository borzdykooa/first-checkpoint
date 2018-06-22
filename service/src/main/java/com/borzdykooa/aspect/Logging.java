package com.borzdykooa.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class Logging {

    private static final Logger LOGGER = Logger.getLogger(Logging.class);

    @Pointcut("execution(public * com.borzdykooa.service.*.*(..))")
    public void addLogging() {
    }

    @Around("addLogging()")
    public Object checkLogging(ProceedingJoinPoint point) {
        LOGGER.info("Вызывается метод <" + point.getSignature().getName() + "> с параметрами <" + Arrays.toString(point.getArgs()) + ">");
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("Метод " + point.getSignature().getName() + " пробрасывает исключение " + throwable.getMessage());
            throwable.printStackTrace();
        }
        LOGGER.info("Метод " + point.getSignature().getName() + " возвращает " + result);

        return result;
    }
}