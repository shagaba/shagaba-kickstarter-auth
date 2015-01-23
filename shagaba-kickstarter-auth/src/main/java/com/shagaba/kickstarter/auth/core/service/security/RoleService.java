package com.shagaba.kickstarter.auth.core.service.security;

import java.util.List;

import com.shagaba.kickstarter.auth.core.domain.security.Role;

public interface RoleService {

    public Role getRoleById(String id);
    
    public List<Role> getAllRoles(Iterable<String> ids);

    public List<Role> getAllRoles();

    public Role create(Role role);

    public Role update(Role role);

    public void delete(String id);

}
