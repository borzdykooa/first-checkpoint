package com.borzdykooa.util;

import com.borzdykooa.config.DaoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class UtilClass {

    private static final AnnotationConfigApplicationContext CONTEXT = new AnnotationConfigApplicationContext(DaoConfiguration.class);

    public static <T> T getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }
}
