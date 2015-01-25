package com.shagaba.kickstarter.auth.client.common;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.shagaba.kickstarter.auth.core.domain.security.Authority;


public abstract class CRUDRestClient<T> extends RestClient {
    
    protected String requestMapping = null;
    
    /**
     * @param restComponents
     */
    public CRUDRestClient(RestComponents restComponents, String requestMapping) {
        super(restComponents);
        this.requestMapping = requestMapping;
    }

    /**
     * @return the requestMapping
     */
    public String getRequestMapping() {
        return requestMapping;
    }

    /**
     * @param requestMapping the requestMapping to set
     */
    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    /**
     * @param entity
     * @return
     */
    protected ResponseEntity<T> createEntity(T entity) {
        HttpEntity<T> requestEntity = new HttpEntity<T>(entity, restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(requestMapping).build();
        return restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.POST, requestEntity, new ParameterizedTypeReference<T>(){ });
    }

    /**
     * @return
     */
    protected ResponseEntity<List<T>> getAllEntitys() {
        HttpEntity<Authority> requestEntity = new HttpEntity<Authority>(restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(requestMapping).build();
        return restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<T>>() { });
    }

    /**
     * @param id
     * @return
     */
    protected ResponseEntity<T> getEntityById(String id) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(id, restComponents.getHttpHeaders());
        MultiValueMap<String, String> uriMap = new LinkedMultiValueMap<String, String>();
        uriMap.add("id", id);
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(requestMapping).path("/{id}").build();
        return restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<T>(){ }, uriMap);
    }

    /**
     * @param entity
     * @return
     */
    protected ResponseEntity<T> updateEntity(T entity) {
        HttpEntity<T> requestEntity = new HttpEntity<T>(entity, restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(requestMapping).build();
        return restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<T>(){});
    }

    /**
     * @param id
     */
    protected ResponseEntity<T> deleteEntity(String id) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(id, restComponents.getHttpHeaders());
        MultiValueMap<String, String> uriMap = new LinkedMultiValueMap<String, String>();
        uriMap.add("id", id);
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(requestMapping).path("/{id}").build();
        return restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<T>(){}, uriMap);
    }

}
