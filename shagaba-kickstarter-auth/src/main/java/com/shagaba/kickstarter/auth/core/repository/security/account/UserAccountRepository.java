package com.shagaba.kickstarter.auth.core.repository.security.account;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, String> {
    
    public UserAccount getUserAccountByUsername(String username);

}
