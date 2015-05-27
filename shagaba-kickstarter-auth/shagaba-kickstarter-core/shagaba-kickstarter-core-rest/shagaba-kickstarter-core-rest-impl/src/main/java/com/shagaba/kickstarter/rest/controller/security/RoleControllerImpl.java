package com.shagaba.kickstarter.rest.controller.security;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shagaba.kickstarter.auth.core.service.security.RoleService;
import com.shagaba.kickstarter.common.service.mapper.MappingService;
import com.shagaba.kickstarter.rest.controller.security.RoleController;
import com.shagaba.kickstarter.rest.domain.security.Role;

@RestController
@RequestMapping(value = "/security/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleControllerImpl implements RoleController {
    
    @Autowired
    protected RoleService roleService;

    @Autowired
    protected MappingService mappingService;

    @RequestMapping(method = RequestMethod.POST)
    public Role create(@Valid @RequestBody Role role) {
        com.shagaba.kickstarter.core.domain.security.Role coreRole = mappingService.map(role, com.shagaba.kickstarter.core.domain.security.Role.class);
        coreRole = roleService.create(coreRole);
        return mappingService.map(coreRole, Role.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        List<com.shagaba.kickstarter.core.domain.security.Role> coreRoles = roleService.getAllRoles();
        return mappingService.map(coreRoles, Role.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role getRoleById(@PathVariable("id") String id) {
        com.shagaba.kickstarter.core.domain.security.Role coreRole = roleService.getRoleById(id);
        return mappingService.map(coreRole, Role.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Role update(@Valid @RequestBody Role role) {
        com.shagaba.kickstarter.core.domain.security.Role coreRole = roleService.getRoleById(role.getId());
        mappingService.map(role, coreRole);
        coreRole = roleService.update(coreRole);
        return mappingService.map(coreRole, Role.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        roleService.delete(id);
    }

    
    
}
