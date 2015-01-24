package com.shagaba.kickstarter.auth.core.service.security.account;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount;

public interface UserAccountService {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public UserAccount getUserAccountByUsername(String username);
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public UserAccount getUserAccountByAccountId(String accountId);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UserAccount> getAllUserAccounts();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_VISITOR')")
    public UserAccount create(UserAccount userAccount);

    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #userAccount.accountId == authenticatuin.accountId)")
    public UserAccount update(UserAccount userAccount);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(String accountId);

}
