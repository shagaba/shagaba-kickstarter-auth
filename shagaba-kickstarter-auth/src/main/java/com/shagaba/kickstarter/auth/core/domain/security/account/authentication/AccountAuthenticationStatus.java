package com.shagaba.kickstarter.auth.core.domain.security.account.authentication;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shagaba.kickstarter.auth.core.common.audit.AuditingDomain;

@Document
public class AccountAuthenticationStatus extends AuditingDomain {

    @Id
    protected String accountId;

    protected boolean isRegistered = false;
    
    protected boolean isEmailVerified = false;
    
    protected AccountToken accountToken;

    protected AuthenticationTraking logingAuthTraking;
    
    protected AuthenticationTraking tokenAuthTraking;
    
    protected Date lockoutExperation;

    protected LockoutType lockoutType = null;
    
    protected PasswordStatus passwordStatus;

    /**
     * 
     */
    public AccountAuthenticationStatus() {
        super();
    }

    /**
     * @param accountId
     * @param isRegistered
     * @param isEmailVerified
     */
    public AccountAuthenticationStatus(String accountId, boolean isRegistered, boolean isEmailVerified) {
        super();
        this.accountId = accountId;
        this.isRegistered = isRegistered;
        this.isEmailVerified = isEmailVerified;
    }

    /**
     * @param accountId
     * @param isRegistered
     * @param isEmailVerified
     * @param accountToken
     * @param logingAuthTraking
     * @param tokenAuthTraking
     * @param lockoutExperation
     * @param lockoutType
     * @param passwordStatus
     */
    public AccountAuthenticationStatus(String accountId, boolean isRegistered, boolean isEmailVerified, AccountToken accountToken, AuthenticationTraking logingAuthTraking, AuthenticationTraking tokenAuthTraking, Date lockoutExperation, LockoutType lockoutType, PasswordStatus passwordStatus) {
        super();
        this.accountId = accountId;
        this.isRegistered = isRegistered;
        this.isEmailVerified = isEmailVerified;
        this.accountToken = accountToken;
        this.logingAuthTraking = logingAuthTraking;
        this.tokenAuthTraking = tokenAuthTraking;
        this.lockoutExperation = lockoutExperation;
        this.lockoutType = lockoutType;
        this.passwordStatus = passwordStatus;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the isRegistered
     */
    public boolean isRegistered() {
        return isRegistered;
    }

    /**
     * @param isRegistered the isRegistered to set
     */
    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    /**
     * @return the isEmailVerified
     */
    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    /**
     * @param isEmailVerified the isEmailVerified to set
     */
    public void setEmailVerified(boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    /**
     * @return the accountToken
     */
    public AccountToken getAccountToken() {
        return accountToken;
    }

    /**
     * @param accountToken the accountToken to set
     */
    public void setAccountToken(AccountToken accountToken) {
        this.accountToken = accountToken;
    }

    /**
     * @return the logingAuthTraking
     */
    public AuthenticationTraking getLogingAuthTraking() {
        return logingAuthTraking;
    }

    /**
     * @param logingAuthTraking the logingAuthTraking to set
     */
    public void setLogingAuthTraking(AuthenticationTraking logingAuthTraking) {
        this.logingAuthTraking = logingAuthTraking;
    }

    /**
     * @return the passwordAuthTraking
     */
    public AuthenticationTraking getTokenAuthTraking() {
        return tokenAuthTraking;
    }

    /**
     * @param tokenAuthTraking the tokenAuthTraking to set
     */
    public void setTokenAuthTraking(AuthenticationTraking tokenAuthTraking) {
        this.tokenAuthTraking = tokenAuthTraking;
    }

    /**
     * @return the lockoutExperation
     */
    public Date getLockoutExperation() {
        return lockoutExperation;
    }

    /**
     * @param lockoutExperation the lockoutExperation to set
     */
    public void setLockoutExperation(Date lockoutExperation) {
        this.lockoutExperation = lockoutExperation;
    }

    /**
     * @return the lockoutType
     */
    public LockoutType getLockoutType() {
        return lockoutType;
    }

    /**
     * @param lockoutType the lockoutType to set
     */
    public void setLockoutType(LockoutType lockoutType) {
        this.lockoutType = lockoutType;
    }

    /**
     * @return the passwordStatus
     */
    public PasswordStatus getPasswordStatus() {
        return passwordStatus;
    }

    /**
     * @param passwordStatus the passwordStatus to set
     */
    public void setPasswordStatus(PasswordStatus passwordStatus) {
        this.passwordStatus = passwordStatus;
    }
    
    
}
