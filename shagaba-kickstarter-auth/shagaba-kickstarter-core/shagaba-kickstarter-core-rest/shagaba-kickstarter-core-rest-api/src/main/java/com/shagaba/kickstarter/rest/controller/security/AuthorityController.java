package com.shagaba.kickstarter.rest.controller.security;

import java.util.List;

import com.shagaba.kickstarter.rest.domain.security.Authority;

public interface AuthorityController {

	public Authority create(Authority authority);

	public List<Authority> getAllAuthorities();

	public Authority getAuthorityById(String id);

	public Authority update(Authority authority);

	public void delete(String id);

}