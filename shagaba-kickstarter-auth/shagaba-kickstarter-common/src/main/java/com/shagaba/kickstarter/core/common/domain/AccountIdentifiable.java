package com.shagaba.kickstarter.core.common.domain;

import java.io.Serializable;

public interface AccountIdentifiable<ID extends Serializable> {
	
	/**
	 * @return the accountId
	 */
	public ID getAccountId();

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(ID accountId);
	
	

}
