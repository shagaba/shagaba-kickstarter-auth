package com.shagaba.kickstarter.auth.client.common;

import org.springframework.util.Assert;


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
     * @return the restComponents
     */
    public RestComponents getRestComponents() {
        return restComponents;
    }

}
