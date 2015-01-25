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
import com.shagaba.kickstarter.auth.core.service.security.RoleService;
import com.shagaba.kickstarter.auth.rest.domain.security.Role;

@RestController
@RequestMapping(value = "/security/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    
    @Resource
    protected RoleService roleService;

    @Resource
    protected MappingService mappingService;

    @RequestMapping(method = RequestMethod.POST)
    public Role create(@Valid @RequestBody Role role) {
        com.shagaba.kickstarter.auth.core.domain.security.Role coreRole = mappingService.map(role, com.shagaba.kickstarter.auth.core.domain.security.Role.class);
        coreRole = roleService.create(coreRole);
        return mappingService.map(coreRole, Role.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        List<com.shagaba.kickstarter.auth.core.domain.security.Role> coreRoles = roleService.getAllRoles();
        return mappingService.map(coreRoles, Role.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role getRoleById(@PathVariable("id") String id) {
        com.shagaba.kickstarter.auth.core.domain.security.Role coreRole = roleService.getRoleById(id);
        return mappingService.map(coreRole, Role.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Role update(@Valid @RequestBody Role role) {
        com.shagaba.kickstarter.auth.core.domain.security.Role coreRole = mappingService.map(role, com.shagaba.kickstarter.auth.core.domain.security.Role.class);
        coreRole = roleService.update(coreRole);
        return mappingService.map(coreRole, Role.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        roleService.delete(id);
    }

    
    
}
