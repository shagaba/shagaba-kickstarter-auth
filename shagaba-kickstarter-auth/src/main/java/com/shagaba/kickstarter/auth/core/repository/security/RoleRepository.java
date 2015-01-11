package com.shagaba.kickstarter.auth.core.repository.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shagaba.kickstarter.auth.core.domain.security.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

}
