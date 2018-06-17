package com.borzdykooa.config;

import com.borzdykooa.aspect.Logging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.borzdykooa.service")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@Import({DaoConfiguration.class, CashingConfig.class})
public class ServiceConfiguration {

    @Bean
    public Logging logging() {
        return new Logging();
    }
}
