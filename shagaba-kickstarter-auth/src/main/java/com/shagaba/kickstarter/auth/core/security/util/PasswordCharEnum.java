package com.shagaba.kickstarter.auth.core.security.util;

public enum PasswordCharEnum {
	
	NUMERIC("123467890"), 
	SYMBOL("!@#%&*-+="), 
	ALPHABETIC_LOWER_CASE("abcdefghijklmnopqrstuvwxyz"), 
	ALPHABETIC_UPPER_CASE("ABCDEFGHIJKLMNOPQRSTUVWXYZ"), 
	ALPHANUMERIC_NUMERIC("2346789"),
	ALPHANUMERIC_ALPHABETIC_LOWER_CASE("abcdefghjkmnpqrstuvwxy"),
	ALPHANUMERIC_ALPHABETIC_UPPER_CASE("ABCDEFGHJLMNPQRSTUVWXY");

	private String characters;
	
	/**
	 * @param characters
	 */
	private PasswordCharEnum(String characters) {
		this.characters = characters;
	}

	/**
	 * @return the characters
	 */
	public String getCharacters() {
		return characters;
	}

	
}
