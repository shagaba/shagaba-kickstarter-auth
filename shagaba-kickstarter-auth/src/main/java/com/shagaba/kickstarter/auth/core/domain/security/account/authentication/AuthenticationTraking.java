package com.shagaba.kickstarter.auth.core.domain.security.account.authentication;

import java.util.List;


public class AuthenticationTraking {

    protected int failCount = 0;

    protected List<AuthenticationAttempt> authenticationAttempts;

    /**
     * @return the failCount
     */
    public int getFailCount() {
        return failCount;
    }

    /**
     * @param failCount the failCount to set
     */
    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    /**
     * @return the authenticationAttempts
     */
    public List<AuthenticationAttempt> getAuthenticationAttempts() {
        return authenticationAttempts;
    }

    /**
     * @param authenticationAttempts the authenticationAttempts to set
     */
    public void setAuthenticationAttempts(List<AuthenticationAttempt> authenticationAttempts) {
        this.authenticationAttempts = authenticationAttempts;
    }

}
