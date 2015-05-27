package com.shagaba.kickstarter.core.domain.security.account.authentication;

import java.util.Date;

public class PasswordStatus {

	protected boolean isPasswordChangeRequired = false;

    protected Date experationTime;

    protected Date modifiedTimestamp;

    /**
	 * 
	 */
	public PasswordStatus() {
        super();
	}

	/**
	 * @param isPasswordChangeRequired
	 * @param experationTime
	 * @param modifiedTimestamp
	 */
	public PasswordStatus(boolean isPasswordChangeRequired,	Date experationTime, Date modifiedTimestamp) {
        super();
		this.isPasswordChangeRequired = isPasswordChangeRequired;
		this.experationTime = experationTime;
		this.modifiedTimestamp = modifiedTimestamp;
	}

    /**
     * @return the isPasswordChangeRequired
     */
    public boolean isPasswordChangeRequired() {
        return isPasswordChangeRequired;
    }

    /**
     * @param isPasswordChangeRequired the isPasswordChangeRequired to set
     */
    public void setPasswordChangeRequired(boolean isPasswordChangeRequired) {
        this.isPasswordChangeRequired = isPasswordChangeRequired;
    }

    /**
     * @return the experationTime
     */
    public Date getExperationTime() {
        return experationTime;
    }

    /**
     * @param experationTime the experationTime to set
     */
    public void setExperationTime(Date experationTime) {
        this.experationTime = experationTime;
    }

    /**
     * @return the modifiedTimestamp
     */
    public Date getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    /**
     * @param modifiedTimestamp the modifiedTimestamp to set
     */
    public void setModifiedTimestamp(Date modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("PasswordStatus {isPasswordChangeRequired=%s, experationTime=%s, modifiedTimestamp=%s}",
				isPasswordChangeRequired, experationTime, modifiedTimestamp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((experationTime == null) ? 0 : experationTime.hashCode());
		result = prime * result + (isPasswordChangeRequired ? 1231 : 1237);
		result = prime * result + ((modifiedTimestamp == null) ? 0 : modifiedTimestamp.hashCode());
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
		PasswordStatus other = (PasswordStatus) obj;
		if (experationTime == null) {
			if (other.experationTime != null)
				return false;
		} else if (!experationTime.equals(other.experationTime))
			return false;
		if (isPasswordChangeRequired != other.isPasswordChangeRequired)
			return false;
		if (modifiedTimestamp == null) {
			if (other.modifiedTimestamp != null)
				return false;
		} else if (!modifiedTimestamp.equals(other.modifiedTimestamp))
			return false;
		return true;
	}

}
