package com.shagaba.kickstarter.auth.core.security.util;

import java.security.SecureRandom;
import java.util.Random;

import com.google.common.base.CharMatcher;

public class PasswordUtils {

	public static final int MIN_CHARACTERS_ALLOWED = 5;

	public static final String NUMERIC = "123467890";
	public static final String SPECIAL_CHAR = "!@#%&*-+=";
	public static final String ALPHABETIC_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
	public static final String ALPHABETIC_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String ALPHANUMERIC_NUMERIC = "2346789";
	public static final String ALPHANUMERIC_ALPHABETIC_LOWER_CASE = "abcdefghjkmnpqrstuvwxy";
	public static final String ALPHANUMERIC_ALPHABETIC_UPPER_CASE = "ABCDEFGHJLMNPQRSTUVWXY";

	/**
	 * @param requiredLength
	 * @return
	 */
	public static String generateNumeric(int requiredLength) {
		return generatePasswoed(requiredLength, NUMERIC);
	}

	/**
	 * @param requiredLength
	 * @return
	 */
	public static String generateAlphabetic(int requiredLength) {
		String[] sequence = { ALPHABETIC_LOWER_CASE, ALPHABETIC_UPPER_CASE };
		return generatePasswoed(requiredLength, sequence);
	}

	/**
	 * @param requiredLength
	 * @return
	 */
	public static String generateAlphanumeric(int requiredLength) {
		String[] sequence = { ALPHANUMERIC_NUMERIC, ALPHANUMERIC_ALPHABETIC_LOWER_CASE, ALPHANUMERIC_ALPHABETIC_UPPER_CASE };
		String password = "";
		do {
			password = generatePasswoed(requiredLength, sequence);
		} while (!CharMatcher.anyOf(password).matchesAnyOf(ALPHANUMERIC_NUMERIC)
				|| !CharMatcher.anyOf(password).matchesAnyOf(ALPHANUMERIC_ALPHABETIC_LOWER_CASE)
				|| !CharMatcher.anyOf(password).matchesAnyOf(ALPHANUMERIC_ALPHABETIC_UPPER_CASE));
		return password;
	}

	/**
	 * @param requiredLength
	 * @return
	 */
	public static String generateAlphanumericWithSpechialCharacter(int requiredLength) {
		String[] sequence = { ALPHANUMERIC_NUMERIC, ALPHANUMERIC_ALPHABETIC_LOWER_CASE, ALPHANUMERIC_ALPHABETIC_UPPER_CASE, SPECIAL_CHAR };
		String password = "";
		do {
			password = generatePasswoed(requiredLength, sequence);
		} while (!CharMatcher.anyOf(password).matchesAnyOf(ALPHANUMERIC_NUMERIC)
				|| !CharMatcher.anyOf(password).matchesAnyOf(ALPHANUMERIC_ALPHABETIC_LOWER_CASE)
				|| !CharMatcher.anyOf(password).matchesAnyOf(ALPHANUMERIC_ALPHABETIC_UPPER_CASE)
				|| !CharMatcher.anyOf(password).matchesAnyOf(SPECIAL_CHAR));
		return password;
	}

	/**
	 * @param requiredLength
	 * @param sequence
	 * @return
	 */
	private static String generatePasswoed(int requiredLength, String... sequence) {
		if (requiredLength < 0) {
			throw new IllegalArgumentException("Requested random Character resource length " + requiredLength + " is less than 0.");
		} else if (requiredLength < MIN_CHARACTERS_ALLOWED) {
			throw new IllegalArgumentException("Requested random Character resource length " + requiredLength + " is less than "
					+ MIN_CHARACTERS_ALLOWED + " (minimum Character allowed).");
		}

		Random random = new SecureRandom();
		StringBuffer sb = new StringBuffer(requiredLength);
		for (int i = 0; i < requiredLength; ++i) {

			int index = (int) Math.round(Math.floor(random.nextDouble() * sequence.length));
			sb.append(randomCharacter(sequence[index].toCharArray(), random));
		}
		return sb.toString();
	}

	/**
	 * @param characterArray
	 * @param random
	 * @return
	 */
	private static char randomCharacter(char[] characterArray, Random random) {
		if (characterArray == null)
			throw new IllegalArgumentException("no Character resource");

		int length = characterArray.length;
		return characterArray[(int) Math.round(Math.floor(random.nextDouble() * length))];
	}
}
