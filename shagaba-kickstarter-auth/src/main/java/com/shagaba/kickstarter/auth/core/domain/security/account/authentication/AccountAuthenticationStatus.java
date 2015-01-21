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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountToken == null) ? 0 : accountToken.hashCode());
		result = prime * result + (isEmailVerified ? 1231 : 1237);
		result = prime * result + (isRegistered ? 1231 : 1237);
		result = prime * result + ((lockoutExperation == null) ? 0 : lockoutExperation.hashCode());
		result = prime * result + ((lockoutType == null) ? 0 : lockoutType.hashCode());
		result = prime * result + ((logingAuthTraking == null) ? 0 : logingAuthTraking.hashCode());
		result = prime * result + ((passwordStatus == null) ? 0 : passwordStatus.hashCode());
		result = prime * result + ((tokenAuthTraking == null) ? 0 : tokenAuthTraking.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountAuthenticationStatus other = (AccountAuthenticationStatus) obj;
		if (accountToken == null) {
			if (other.accountToken != null)
				return false;
		} else if (!accountToken.equals(other.accountToken))
			return false;
		if (isEmailVerified != other.isEmailVerified)
			return false;
		if (isRegistered != other.isRegistered)
			return false;
		if (lockoutExperation == null) {
			if (other.lockoutExperation != null)
				return false;
		} else if (!lockoutExperation.equals(other.lockoutExperation))
			return false;
		if (lockoutType != other.lockoutType)
			return false;
		if (logingAuthTraking == null) {
			if (other.logingAuthTraking != null)
				return false;
		} else if (!logingAuthTraking.equals(other.logingAuthTraking))
			return false;
		if (passwordStatus == null) {
			if (other.passwordStatus != null)
				return false;
		} else if (!passwordStatus.equals(other.passwordStatus))
			return false;
		if (tokenAuthTraking == null) {
			if (other.tokenAuthTraking != null)
				return false;
		} else if (!tokenAuthTraking.equals(other.tokenAuthTraking))
			return false;
		return true;
	}
    
    
}
