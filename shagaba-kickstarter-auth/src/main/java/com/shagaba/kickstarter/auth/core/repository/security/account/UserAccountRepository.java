package com.shagaba.kickstarter.auth.core.repository.security.account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
    
    public UserAccount getUserAccountByUsername(String username);

}
