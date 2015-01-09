package com.shagaba.kickstarter.auth.core.domain.security.account;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserAccountDetails extends org.springframework.security.core.userdetails.User {

    protected String accountId;
    protected String salt;
    
    /**
     * @param accountId
     * @param username
     * @param password
     * @param salt
     * @param authorities
     */
    public UserAccountDetails(String accountId, String username, String password, String salt, Collection<? extends GrantedAuthority> authorities) {
        this(accountId, username, password, salt, true, true, true, true, authorities);
    }
    
    /**
     * @param accountId
     * @param username
     * @param password
     * @param salt
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */
    public UserAccountDetails(String accountId, String username, String password, String salt, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.accountId = accountId;
        this.salt = salt;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

}
