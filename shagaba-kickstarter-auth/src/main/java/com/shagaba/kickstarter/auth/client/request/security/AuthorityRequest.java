package com.shagaba.kickstarter.auth.client.request.security;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.shagaba.kickstarter.auth.client.common.RestClient;
import com.shagaba.kickstarter.auth.client.common.RestComponents;
import com.shagaba.kickstarter.auth.rest.domain.security.Authority;

public class AuthorityRequest extends RestClient {
    public static final String REQUEST_MAPPING = "/security/authorities";

    /**
     * @param restComponents
     */
    public AuthorityRequest(RestComponents restComponents) {
        super(restComponents);
    }

    /**
     * @param authority
     * @return
     */
    public Authority create(Authority authority) {
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).build();
        HttpEntity<Authority> requestEntity = new HttpEntity<Authority>(authority, restComponents.getHttpHeaders());
        ResponseEntity<Authority> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.POST, requestEntity, Authority.class);

        super.updateHeaderToken(responseEntity);
        return responseEntity.getBody();
    }

    /**
     * @return
     */
    public List<Authority> getAllAuthorities() {
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).build();
        HttpEntity<Authority> requestEntity = new HttpEntity<Authority>(restComponents.getHttpHeaders());
        ResponseEntity<List<Authority>> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Authority>>() { });

        super.updateHeaderToken(responseEntity);
        return responseEntity.getBody();
    }

    /**
     * @param id
     * @return
     */
    public Authority getAuthorityById(String id) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).path("/").path(id).build();
        ResponseEntity<Authority> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.GET, requestEntity, Authority.class);

        super.updateHeaderToken(responseEntity);
        return responseEntity.getBody();
    }

    /**
     * @param authority
     * @return
     */
    public Authority update(Authority authority) {
        HttpEntity<Authority> requestEntity = new HttpEntity<Authority>(authority, restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).build();
        ResponseEntity<Authority> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.PUT, requestEntity, Authority.class);

        super.updateHeaderToken(responseEntity);
        return responseEntity.getBody();
    }

    /**
     * @param id
     */
    public void delete(String id) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).path("/").path(id).build();
        ResponseEntity<Authority> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.DELETE, requestEntity, Authority.class);

        super.updateHeaderToken(responseEntity);
    }

}
