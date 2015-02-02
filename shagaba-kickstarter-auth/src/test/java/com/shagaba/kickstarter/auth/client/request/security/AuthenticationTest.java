package com.shagaba.kickstarter.auth.client.request.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.shagaba.kickstarter.auth.client.common.RestComponents;
import com.shagaba.kickstarter.auth.client.config.HttpConfig;
import com.shagaba.kickstarter.auth.common.domain.message.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { HttpConfig.class })
public class AuthenticationTest {
    
    @Autowired
    private RestComponents restComponents;

    @Test
    public void authenticate() {
        
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(restComponents);
        Message message = authenticationRequest.authenticateUser("admin", "admin");
        
        Assert.notNull(message);
        Assert.notNull(message.getMessage());
        Assert.isTrue(message.getMessage().equals("Login Success"));
        Assert.isTrue(restComponents.getHttpHeaders().containsKey("X-Auth-Token"));
        System.out.println("X-Auth-Token = " + restComponents.getHttpHeaders().getFirst("X-Auth-Token"));
        
    }
    
}
