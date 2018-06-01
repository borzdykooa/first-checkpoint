package com.borzdykooa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.borzdykooa.service")
//@PropertySource("classpath:database.properties")
//@EnableTransactionManagement
@Import(PersistenceDaoConfiguration.class)
public class PersistenceServiceConfiguration {
}














