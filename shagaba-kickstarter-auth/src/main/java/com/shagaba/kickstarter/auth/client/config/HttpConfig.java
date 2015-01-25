package com.shagaba.kickstarter.auth.client.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.shagaba.kickstarter.auth.client")
public class HttpConfig {
    
    protected int readTimeout = 2000;
    
    protected int connectTimeout = 2000;
    
    @Bean
    @Scope("prototype")
    public RestOperations restOperations() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    @Scope("prototype")
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(readTimeout);
//        factory.setConnectTimeout(connectTimeout);
        return factory;
    }   
    
    @Bean
    @Scope("prototype")
    protected HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

}
