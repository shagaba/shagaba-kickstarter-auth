package com.shagaba.kickstarter.auth.rest.domain.security.account;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserAccount {

	protected String id;

	@NotNull
	@Size(min = 5, max = 30)
	protected String username;

	@NotNull
	@Size(min = 5, max = 23)
	protected String password;

	@NotNull
	@Email
	protected String emailAddress;

	protected List<ChallengeQuestion> challengeQuestions;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("UserAccount {id=%s, username=%s, password=%s, emailAddress=%s, challengeQuestions=%s}", id,
				username, password, emailAddress, challengeQuestions);
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((challengeQuestions == null) ? 0 : challengeQuestions.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
