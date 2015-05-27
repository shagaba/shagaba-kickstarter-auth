package com.shagaba.kickstarter.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

import com.shagaba.kickstarter.application.config.ApplicationConfig;

@Import(ApplicationConfig.class)
public class BootApplication implements CommandLineRunner {
    
    @Override
    public void run(String... arg0) throws Exception {

    }

    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootApplication.class, args);
    }
}
