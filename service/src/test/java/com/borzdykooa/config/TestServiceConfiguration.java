package com.borzdykooa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.borzdykooa.util")
@Import(ServiceConfiguration.class)
public class TestServiceConfiguration {
}
