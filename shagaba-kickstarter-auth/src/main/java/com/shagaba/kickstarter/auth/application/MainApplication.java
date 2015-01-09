package com.shagaba.kickstarter.auth.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

import com.shagaba.kickstarter.auth.application.config.ApplicationConfig;

@Import(ApplicationConfig.class)
public class MainApplication implements CommandLineRunner {
    
    @Override
    public void run(String... arg0) throws Exception {

    }

    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
    }
}
