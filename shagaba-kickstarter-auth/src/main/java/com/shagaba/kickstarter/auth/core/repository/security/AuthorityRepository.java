package com.shagaba.kickstarter.auth.core.repository.security;

import javax.annotation.Resource;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.auth.core.domain.security.Authority;

@Resource
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, String> {

}
