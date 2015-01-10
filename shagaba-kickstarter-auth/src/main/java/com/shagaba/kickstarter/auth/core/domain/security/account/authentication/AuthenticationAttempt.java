package com.shagaba.kickstarter.auth.core.domain.security.account.authentication;

import java.util.Date;


public class AuthenticationAttempt {
    
    protected String ip;
    
    protected Date timestamp;
    
    protected AuthenticationAttemptStatus authenticationAttemptStatus;

    /**
     * 
     */
    public AuthenticationAttempt() {
        super();
    }

    /**
     * @param ip
     * @param timestamp
     * @param authenticationAttemptStatus
     */
    public AuthenticationAttempt(String ip, Date timestamp, AuthenticationAttemptStatus authenticationAttemptStatus) {
        super();
        this.ip = ip;
        this.timestamp = timestamp;
        this.authenticationAttemptStatus = authenticationAttemptStatus;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the authenticationAttemptStatus
     */
    public AuthenticationAttemptStatus getAuthenticationAttemptStatus() {
        return authenticationAttemptStatus;
    }

    /**
     * @param authenticationAttemptStatus the authenticationAttemptStatus to set
     */
    public void setAuthenticationAttemptStatus(AuthenticationAttemptStatus authenticationAttemptStatus) {
        this.authenticationAttemptStatus = authenticationAttemptStatus;
    }

}
