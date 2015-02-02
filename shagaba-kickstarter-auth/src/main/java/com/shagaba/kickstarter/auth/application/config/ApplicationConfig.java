package com.shagaba.kickstarter.auth.application.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.shagaba.kickstarter.auth.application.init.InitializeMongoDB;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.shagaba.kickstarter.auth" })
public class ApplicationConfig {

    @Bean
    public InitializeMongoDB initializeMongoDB(){
        return new InitializeMongoDB();
    }

}
