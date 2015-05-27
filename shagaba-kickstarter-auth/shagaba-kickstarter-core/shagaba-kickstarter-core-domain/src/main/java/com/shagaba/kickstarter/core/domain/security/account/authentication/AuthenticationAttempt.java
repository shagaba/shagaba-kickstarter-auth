package com.shagaba.kickstarter.core.domain.security.account.authentication;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("AuthenticationAttempt {ip=%s, timestamp=%s, authenticationAttemptStatus=%s}", ip, timestamp,
				authenticationAttemptStatus);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authenticationAttemptStatus == null) ? 0 : authenticationAttemptStatus.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		AuthenticationAttempt other = (AuthenticationAttempt) obj;
		if (authenticationAttemptStatus != other.authenticationAttemptStatus)
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
