package com.shagaba.kickstarter.auth.client.common;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.shagaba.kickstarter.auth.client.request.security.AuthenticationRequest;
import com.shagaba.kickstarter.auth.client.request.security.AuthorityRequest;


public class RestClient {
    
    protected RestComponents restComponents = null;

    /**
     * @param restComponents
     */
    public RestClient(RestComponents restComponents) {
        super();
        Assert.notNull(restComponents, "RestComponents required");
        this.restComponents = restComponents;
    }

    /**
     * @param responseEntity
     */
    protected void updateHeaderToken(ResponseEntity<?> responseEntity) {
    	restComponents.setHttpHeaders(responseEntity.getHeaders());
    }

    /**
     * @return the restComponents
     */
    public RestComponents getRestComponents() {
        return restComponents;
    }
    
    /**
     * @return the authenticationRequest
     */
    public AuthenticationRequest authenticationRequest() {
        return new AuthenticationRequest(restComponents);
    }
    
    /**
     * @return the authorityRequest
     */
    public AuthorityRequest authorityRequest() {
        return new AuthorityRequest(restComponents);
    }

}
