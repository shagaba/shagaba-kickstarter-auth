package com.shagaba.kickstarter.auth.core.service.security;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.shagaba.kickstarter.core.domain.security.Authority;

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ADMIN_AUTHORITY_SERVICE')")
public interface AuthorityService {

    public Authority getAuthorityById(String id);

    public List<Authority> getAllAuthorities(Iterable<String> ids);

    public List<Authority> getAllAuthorities();

    public Authority create(Authority authority);

    public Authority update(Authority authority);

    public void delete(String id);

}