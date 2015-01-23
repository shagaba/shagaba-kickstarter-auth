package com.shagaba.kickstarter.auth.core.service.security;

import java.util.List;

import com.shagaba.kickstarter.auth.core.domain.security.Authority;

public interface AuthorityService {

    public Authority getAuthorityById(String id);

    public List<Authority> getAllAuthorities(Iterable<String> ids);

    public List<Authority> getAllAuthorities();

    public Authority create(Authority authority);

    public Authority update(Authority authority);

    public void delete(String id);

}