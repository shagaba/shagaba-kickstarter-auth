package com.shagaba.kickstarter.auth.core.domain.security.account;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserAccountDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 6439888840643653849L;
	protected String accountId;
	protected String salt;

	/**
	 * @param accountId
	 * @param username
	 * @param password
	 * @param salt
	 * @param authorities
	 */
	public UserAccountDetails(String accountId, String username, String password, String salt,
			Collection<? extends GrantedAuthority> authorities) {
		this(accountId, username, password, salt, true, true, true, true, authorities);
	}

	/**
	 * @param accountId
	 * @param username
	 * @param password
	 * @param salt
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public UserAccountDetails(String accountId, String username, String password, String salt, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.accountId = accountId;
		this.salt = salt;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccountDetails other = (UserAccountDetails) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		return true;
	}

}
