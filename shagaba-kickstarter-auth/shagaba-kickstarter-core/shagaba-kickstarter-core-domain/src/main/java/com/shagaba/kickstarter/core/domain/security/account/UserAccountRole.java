package com.shagaba.kickstarter.core.domain.security.account;

import java.util.Date;

public class UserAccountRole {

	protected String roleId;

	protected Date experationTime;

	/**
     * 
     */
	public UserAccountRole() {
		super();
	}

	/**
	 * @param roleId
	 * @param experationTime
	 */
	public UserAccountRole(String roleId, Date experationTime) {
		super();
		this.roleId = roleId;
		this.experationTime = experationTime;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the experationTime
	 */
	public Date getExperationTime() {
		return experationTime;
	}

	/**
	 * @param experationTime
	 *            the experationTime to set
	 */
	public void setExperationTime(Date experationTime) {
		this.experationTime = experationTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("UserAccountRole {roleId=%s, experationTime=%s}", roleId, experationTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((experationTime == null) ? 0 : experationTime.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		UserAccountRole other = (UserAccountRole) obj;
		if (experationTime == null) {
			if (other.experationTime != null)
				return false;
		} else if (!experationTime.equals(other.experationTime))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

}
