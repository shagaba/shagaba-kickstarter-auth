package com.shagaba.kickstarter.auth.rest.controller.security;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shagaba.kickstarter.auth.common.service.mapper.MappingService;
import com.shagaba.kickstarter.auth.core.service.security.AuthorityService;
import com.shagaba.kickstarter.auth.rest.domain.security.Authority;

@RestController
@RequestMapping(value = "/security/authorities", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorityController {
    
    @Resource
    protected AuthorityService authorityService;

    @Resource
    protected MappingService mappingService;

    @RequestMapping(method = RequestMethod.POST)
    public Authority create(@Valid @RequestBody Authority authority) {
        com.shagaba.kickstarter.auth.core.domain.security.Authority coreAuthority = mappingService.map(authority, com.shagaba.kickstarter.auth.core.domain.security.Authority.class);
        coreAuthority = authorityService.create(coreAuthority);
        return mappingService.map(coreAuthority, Authority.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Authority> getAllAuthorities() {
        List<com.shagaba.kickstarter.auth.core.domain.security.Authority> coreAuthorities = authorityService.getAllAuthorities();
        return mappingService.map(coreAuthorities, Authority.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Authority getAuthorityById(@PathVariable("id") String id) {
        com.shagaba.kickstarter.auth.core.domain.security.Authority coreAuthority =  authorityService.getAuthorityById(id);
        return mappingService.map(coreAuthority, Authority.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Authority update(@Valid @RequestBody Authority authority) {
        com.shagaba.kickstarter.auth.core.domain.security.Authority coreAuthority = mappingService.map(authority, com.shagaba.kickstarter.auth.core.domain.security.Authority.class);
        coreAuthority = authorityService.update(coreAuthority);
        return mappingService.map(coreAuthority, Authority.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        authorityService.delete(id);
    }
    
}
