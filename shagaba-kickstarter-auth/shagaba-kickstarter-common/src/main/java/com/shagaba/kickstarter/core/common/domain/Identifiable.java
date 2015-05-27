package com.shagaba.kickstarter.core.common.domain;

import java.io.Serializable;

public interface Identifiable<ID extends Serializable> {
	
	/**
	 * @return the id
	 */
	public ID getId();

	/**
	 * @param id the id to set
	 */
	public void setId(ID id);
	
	

}
