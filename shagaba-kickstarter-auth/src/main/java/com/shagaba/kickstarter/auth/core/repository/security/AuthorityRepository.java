package com.shagaba.kickstarter.auth.core.repository.security;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.auth.core.domain.security.Authority;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, String> {

}
