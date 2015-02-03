package com.shagaba.kickstarter.auth.core.security.util;

public enum PasswordTypeEnum {
	
	NUMERIC(PasswordCharEnum.NUMERIC),
	ALPHABETIC(PasswordCharEnum.ALPHABETIC_LOWER_CASE, PasswordCharEnum.ALPHABETIC_UPPER_CASE),
	ALPHANUMERIC(PasswordCharEnum.ALPHABETIC_LOWER_CASE, PasswordCharEnum.ALPHABETIC_UPPER_CASE, PasswordCharEnum.NUMERIC),
	ALPHANUMERIC_SYMBOL(PasswordCharEnum.ALPHABETIC_LOWER_CASE, PasswordCharEnum.ALPHABETIC_UPPER_CASE, PasswordCharEnum.NUMERIC, PasswordCharEnum.SYMBOL);
	
	private PasswordCharEnum[] passwordCharEnums;
	private String[] sequence;
	
	/**
	 * @param passwordCharEnums
	 */
	private PasswordTypeEnum(PasswordCharEnum... passwordCharEnums) {
		this.passwordCharEnums = passwordCharEnums;
		String[] sequence = new String[passwordCharEnums.length];
		for (int i = 0; i < passwordCharEnums.length; ++i) {
			sequence[i] = passwordCharEnums[i].getCharacters();
		}
	}

	/**
	 * @return the passwordCharEnums
	 */
	public PasswordCharEnum[] getPasswordCharEnums() {
		return passwordCharEnums;
	}

	/**
	 * @return the sequence
	 */
	public String[] getSequence() {
		return sequence;
	}
	
}
