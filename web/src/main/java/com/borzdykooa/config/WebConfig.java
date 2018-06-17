package com.borzdykooa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.borzdykooa.controller"})
@Import(value = {InternationalizationConfig.class, ThymeleafConfig.class, SecurityConfig.class})
public class WebConfig {
}
