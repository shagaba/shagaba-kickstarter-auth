package com.shagaba.kickstarter.auth.core.domain.security.account.authentication;

import java.util.Date;

public class AccountToken {

    protected String username;

    protected String signature;

    protected Date signatureTimestamp;

    protected Date timestamp;

    /**
     * 
     */
    public AccountToken() {
    }

    /**
     * @param username
     * @param signature
     * @param signatureTimestamp
     * @param timestamp
     */
    public AccountToken(String username, String signature, Date signatureTimestamp, Date timestamp) {
        super();
        this.username = username;
        this.signature = signature;
        this.signatureTimestamp = signatureTimestamp;
        this.timestamp = timestamp;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature
     *            the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return the signatureTimestamp
     */
    public Date getSignatureTimestamp() {
        return signatureTimestamp;
    }

    /**
     * @param signatureTimestamp
     *            the signatureTimestamp to set
     */
    public void setSignatureTimestamp(Date signatureTimestamp) {
        this.signatureTimestamp = signatureTimestamp;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((signatureTimestamp == null) ? 0 : signatureTimestamp.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		AccountToken other = (AccountToken) obj;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (signatureTimestamp == null) {
			if (other.signatureTimestamp != null)
				return false;
		} else if (!signatureTimestamp.equals(other.signatureTimestamp))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
