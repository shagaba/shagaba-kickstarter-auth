package com.shagaba.kickstarter.auth.client.request.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.shagaba.kickstarter.auth.client.common.RestClient;
import com.shagaba.kickstarter.auth.client.common.RestComponents;
import com.shagaba.kickstarter.auth.common.domain.message.Message;
import com.shagaba.kickstarter.auth.common.domain.message.SimpleMessage;

public class AuthenticationRequest extends RestClient {
    public static final String AUTHENTICATE_USER_REQUEST_MAPPING = "/security/user/authentication";
    
    /**
     * @param restComponents
     */
    public AuthenticationRequest(RestComponents restComponents) {
        super(restComponents);
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public Message authenticateUser(String username, String password) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", username);
        params.add("password", password);
        
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(AUTHENTICATE_USER_REQUEST_MAPPING).queryParams(params).build();

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(restComponents.getHttpHeaders());
        ResponseEntity<SimpleMessage> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.POST, requestEntity, SimpleMessage.class);
        super.updateHeaderToken(responseEntity);
        return responseEntity.getBody();
    }
    
}
