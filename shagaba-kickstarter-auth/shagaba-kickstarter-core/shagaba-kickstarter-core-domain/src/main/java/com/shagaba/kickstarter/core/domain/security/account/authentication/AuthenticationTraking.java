package com.shagaba.kickstarter.core.domain.security.account.authentication;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("AuthenticationTraking {failCount=%s, authenticationAttempts=%s}", failCount, authenticationAttempts);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authenticationAttempts == null) ? 0 : authenticationAttempts.hashCode());
		result = prime * result + failCount;
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
		AuthenticationTraking other = (AuthenticationTraking) obj;
		if (authenticationAttempts == null) {
			if (other.authenticationAttempts != null)
				return false;
		} else if (!authenticationAttempts.equals(other.authenticationAttempts))
			return false;
		if (failCount != other.failCount)
			return false;
		return true;
	}

}
