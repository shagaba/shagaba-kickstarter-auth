package com.shagaba.kickstarter.application.config;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {
    
    @Bean(name="dozerBeanMapper")
    public DozerBeanMapper dozerBeanMapper() {
        return new DozerBeanMapper(Arrays.asList("dozer/dozer-global-configuration.xml", "dozer/dozer-bean-mappings.xml"));
    }
}
