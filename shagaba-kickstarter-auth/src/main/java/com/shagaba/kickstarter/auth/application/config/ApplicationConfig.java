package com.shagaba.kickstarter.auth.application.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.shagaba.kickstarter.auth")
public class ApplicationConfig {

}
