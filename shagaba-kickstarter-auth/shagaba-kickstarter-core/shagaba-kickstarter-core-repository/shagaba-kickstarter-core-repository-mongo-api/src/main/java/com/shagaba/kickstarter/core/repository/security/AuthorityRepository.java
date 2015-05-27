package com.shagaba.kickstarter.core.repository.security;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.core.domain.security.Authority;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, String> {

}
