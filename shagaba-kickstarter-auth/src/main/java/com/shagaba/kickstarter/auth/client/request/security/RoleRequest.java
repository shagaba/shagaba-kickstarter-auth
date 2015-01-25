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
import com.shagaba.kickstarter.auth.rest.domain.security.Role;

public class RoleRequest extends RestClient {
    public static final String REQUEST_MAPPING = "/security/roles";

    /**
     * @param restComponents
     */
    public RoleRequest(RestComponents restComponents) {
        super(restComponents);
    }

    /**
     * @param Role
     * @return
     */
    public Role create(Role Role) {
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).build();
        HttpEntity<Role> requestEntity = new HttpEntity<Role>(Role, restComponents.getHttpHeaders());
        ResponseEntity<Role> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.POST, requestEntity, Role.class);
        return responseEntity.getBody();
    }

    /**
     * @return
     */
    public List<Role> getAllRoles() {
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).build();
        HttpEntity<Role> requestEntity = new HttpEntity<Role>(restComponents.getHttpHeaders());
        ResponseEntity<List<Role>> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Role>>() { });
        return responseEntity.getBody();
    }

    /**
     * @param id
     * @return
     */
    public Role getRoleById(String id) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).path("/").path(id).build();
        ResponseEntity<Role> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.GET, requestEntity, Role.class);
        return responseEntity.getBody();
    }

    /**
     * @param Role
     * @return
     */
    public Role update(Role Role) {
        HttpEntity<Role> requestEntity = new HttpEntity<Role>(Role, restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).build();
        ResponseEntity<Role> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.PUT, requestEntity, Role.class);
        return responseEntity.getBody();
    }

    /**
     * @param id
     */
    public void delete(String id) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(restComponents.getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUri(restComponents.getUri()).path(REQUEST_MAPPING).path("/").path(id).build();
        ResponseEntity<Role> responseEntity = restComponents.getRestOperations().exchange(uriComponents.toUriString(), HttpMethod.DELETE, requestEntity, Role.class);
    }

}
