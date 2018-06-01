package com.borzdykooa.util;

import com.borzdykooa.config.PersistenceDaoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UtilClass {

    private static AnnotationConfigApplicationContext CONTEXT = new AnnotationConfigApplicationContext(PersistenceDaoConfiguration.class);

    public static <T> T getBean (Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }
}
