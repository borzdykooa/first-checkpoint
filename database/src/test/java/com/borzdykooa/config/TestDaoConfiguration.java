package com.borzdykooa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan(basePackages = "com.borzdykooa.")
@Import(PersistenceDaoConfiguration.class)
public class TestDaoConfiguration {
}














