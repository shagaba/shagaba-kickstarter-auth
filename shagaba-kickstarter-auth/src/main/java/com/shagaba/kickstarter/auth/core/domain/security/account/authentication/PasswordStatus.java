package com.shagaba.kickstarter.auth.core.domain.security.account.authentication;

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

}
