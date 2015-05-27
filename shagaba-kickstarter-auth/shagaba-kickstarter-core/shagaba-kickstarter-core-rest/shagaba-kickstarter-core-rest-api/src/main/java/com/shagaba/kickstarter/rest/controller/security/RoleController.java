package com.shagaba.kickstarter.rest.controller.security;

import java.util.List;

import com.shagaba.kickstarter.rest.domain.security.Role;

public interface RoleController {

	public Role create(Role role);

	public List<Role> getAllRoles();

	public Role getRoleById(String id);

	public Role update(Role role);

	public void delete(String id);

}