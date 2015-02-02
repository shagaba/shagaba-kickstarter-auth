package com.shagaba.kickstarter.auth.core.domain.security.account;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shagaba.kickstarter.auth.core.common.audit.AuditingDomain;

@Document
public class UserAccount extends AuditingDomain {

	@Id
	protected String accountId;

	protected String username;

	protected String password;

	protected String emailAddress;

	protected List<ChallengeQuestion> challengeQuestions;

	protected String salt;

	protected List<UserAccountRole> roles;

	/**
     * 
     */
	public UserAccount() {
		super();
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param emailAddress
	 * @param challengeQuestions
	 * @param salt
	 * @param roles
	 */
	public UserAccount(String accountId, String username, String password, String emailAddress, List<ChallengeQuestion> challengeQuestions,
			String salt, List<UserAccountRole> roles) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
		this.challengeQuestions = challengeQuestions;
		this.salt = salt;
		this.roles = roles;
	}

	/**
	 * @return the id
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the challengeQuestions
	 */
	public List<ChallengeQuestion> getChallengeQuestions() {
		return challengeQuestions;
	}

	/**
	 * @param challengeQuestions
	 *            the challengeQuestions to set
	 */
	public void setChallengeQuestions(List<ChallengeQuestion> challengeQuestions) {
		this.challengeQuestions = challengeQuestions;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the roles
	 */
	public List<UserAccountRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<UserAccountRole> roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("UserAccount {id=%s, username=%s, password=%s, emailAddress=%s, challengeQuestions=%s, salt=%s, roles=%s, version=%s, createdBy=%s, createdTime=%s, lastModifiedBy=%s, lastModifiedTime=%s}",
						accountId, username, password, emailAddress, challengeQuestions, salt, roles, version, createdBy, createdTime,
						lastModifiedBy, lastModifiedTime);
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
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((challengeQuestions == null) ? 0 : challengeQuestions.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UserAccount other = (UserAccount) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (challengeQuestions == null) {
			if (other.challengeQuestions != null)
				return false;
		} else if (!challengeQuestions.equals(other.challengeQuestions))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
