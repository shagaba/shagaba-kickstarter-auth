package com.shagaba.kickstarter.auth.core.repository.security.account.authentication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shagaba.kickstarter.auth.core.domain.security.account.authentication.AccountAuthenticationStatus;

@Repository
public interface AccountAuthenticationStatusRepository extends MongoRepository<AccountAuthenticationStatus, String> {

}
