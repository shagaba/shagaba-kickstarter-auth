package com.shagaba.kickstarter.auth.client.request.security;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shagaba.kickstarter.auth.client.common.CRUDRestClient;
import com.shagaba.kickstarter.auth.client.common.RestComponents;
import com.shagaba.kickstarter.auth.rest.domain.security.Authority;

public class AuthorityRequest extends CRUDRestClient<Authority> {
    public static final String REQUEST_MAPPING = "/security/authorities";
    

    /**
     * @param restComponents
     */
    public AuthorityRequest(RestComponents restComponents) {
        super(restComponents, REQUEST_MAPPING);
    }

    /**
     * @param authority
     * @return
     */
    public Authority create(Authority authority) {
        ResponseEntity<Authority> responseEntity = super.createEntity(authority);
        return responseEntity.getBody();
    }

    /**
     * @return
     */
    public List<Authority> getAllAuthorities() {
        ResponseEntity<List<Authority>> responseEntity = super.getAllEntitys();
        return responseEntity.getBody();
    }

    /**
     * @param id
     * @return
     */
    public Authority getAuthorityById(String id) {
        ResponseEntity<Authority> responseEntity = super.getEntityById(id);
        return responseEntity.getBody();
    }

    /**
     * @param authority
     * @return
     */
    public Authority update(Authority authority) {
        ResponseEntity<Authority> responseEntity = super.updateEntity(authority);
        return responseEntity.getBody();
    }

    /**
     * @param id
     */
    public void delete(String id) {
        ResponseEntity<Authority> responseEntity = super.deleteEntity(id);
    }

}
