package com.shagaba.kickstarter.auth.core.service.security;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.shagaba.kickstarter.core.domain.security.Role;

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ADMIN_ROLE_SERVICE')")
public interface RoleService {

    public Role getRoleById(String id);
    
    public List<Role> getAllRoles(Iterable<String> ids);

    public List<Role> getAllRoles();

    public Role create(Role role);

    public Role update(Role role);

    public void delete(String id);

}
