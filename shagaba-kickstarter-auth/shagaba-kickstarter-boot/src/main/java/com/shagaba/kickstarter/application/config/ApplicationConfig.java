package com.shagaba.kickstarter.application.config;

import javax.validation.Validator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.shagaba.kickstarter" })
public class ApplicationConfig {

    @Bean
    public Validator localValidatorFactoryBean() {
       return new LocalValidatorFactoryBean();
    }
    
}
