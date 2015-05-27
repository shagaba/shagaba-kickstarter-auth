package com.shagaba.kickstarter.core.repository.security;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.core.domain.security.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, String> {

}
