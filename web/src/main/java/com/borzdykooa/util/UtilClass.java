package com.borzdykooa.util;

import com.borzdykooa.config.ServiceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class UtilClass {

    private static final AnnotationConfigApplicationContext CONTEXT = new AnnotationConfigApplicationContext(ServiceConfiguration.class);

    public static <T> T getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }
}
