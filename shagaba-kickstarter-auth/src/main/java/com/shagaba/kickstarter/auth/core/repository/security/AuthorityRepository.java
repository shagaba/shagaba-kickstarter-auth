package com.shagaba.kickstarter.auth.core.repository.security;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shagaba.kickstarter.auth.core.domain.security.Authority;

public interface AuthorityRepository extends MongoRepository<Authority, String> {

}
