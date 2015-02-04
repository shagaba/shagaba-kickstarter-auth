package com.shagaba.kickstarter.auth.core.security.util;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.common.base.CharMatcher;

public class SecurePasswordUtils {

	public static final int MIN_CHARACTERS_ALLOWED = 6;
	public static final String NUMERIC = "123467890";
	public static final String SYMBOL = "`~!@#$%^&*()_-+={}[]\\|:;\"'<>,.?/";
	public static final String ALPHABETIC_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
	public static final String ALPHABETIC_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String SIMILAR_CHAR = "oO0ilI1!zZ5$K^";
	public static final String AMBIGUOUS_SYMBOL = "{}[]()/\\'\"`~,;:.<>";
	
	public enum PasswordType{NUMERIC, ALPHABETIC, ALPHANUMERIC, ALPHANUMERIC_SYMBOL};

	/**
	 * @param requiredLength
	 * @param passwordType
	 * @return
	 */
	public static String generatePasswoed(int requiredLength, PasswordType passwordType) {
		return generatePasswoed(requiredLength, passwordType, true, true);
	}
	
	/**
	 * @param requiredLength
	 * @param passwordType
	 * @param excludeSimilarChar
	 * @param excludeAmbiguousSymbols
	 * @return
	 */
	public static String generatePasswoed(int requiredLength, PasswordType passwordType, boolean excludeSimilarChar, boolean excludeAmbiguousSymbols) {
		String[] sequence = getSequence(passwordType);
		sequence = filterSequence(excludeSimilarChar, excludeAmbiguousSymbols, sequence);
		return generatePasswoed(requiredLength, sequence);
	}
	
	/**
	 * @param excludeSimilarChar
	 * @param excludeAmbiguousSymbols
	 * @param sequence
	 * @return
	 */
	public static String[] filterSequence(boolean excludeSimilarChar, boolean excludeAmbiguousSymbols, String... sequence) {
		if (sequence == null)
			return null;
		
		String[] returnSequence = new String[sequence.length];
		for (int i = 0; i < sequence.length; ++i) {
			returnSequence[i] = sequence[i];
			if (excludeSimilarChar) {
				returnSequence[i] = CharMatcher.anyOf(SIMILAR_CHAR).removeFrom(returnSequence[i]);
			}
			if (excludeAmbiguousSymbols) {
				returnSequence[i] = CharMatcher.anyOf(AMBIGUOUS_SYMBOL).removeFrom(returnSequence[i]);
			}
		}
		return returnSequence;
	}

	/**
	 * @param passwordType
	 * @return
	 */
	public static String[] getSequence(PasswordType passwordType) {
		switch (passwordType) {
		case NUMERIC:
			return new String[]{ NUMERIC };
			
		case ALPHABETIC:
			return new String[]{ ALPHABETIC_LOWER_CASE, ALPHABETIC_UPPER_CASE };
			
		case ALPHANUMERIC:
			return new String[]{ ALPHABETIC_LOWER_CASE, ALPHABETIC_UPPER_CASE, NUMERIC };
			
		case ALPHANUMERIC_SYMBOL:
			return new String[]{ ALPHABETIC_LOWER_CASE, ALPHABETIC_UPPER_CASE, NUMERIC, SYMBOL };

		default:
			return new String[]{ ALPHABETIC_LOWER_CASE, ALPHABETIC_UPPER_CASE, NUMERIC, SYMBOL };
		}
	}

	/**
	 * @param requiredLength
	 * @param sequence
	 * @return
	 */
	public static String generatePasswoed(int requiredLength, String... sequence) {
		Random random = new SecureRandom();
		return generatePasswoed(requiredLength, random, sequence);
	}
	
	/**
	 * @param requiredLength
	 * @param random
	 * @param sequence
	 * @return
	 */
	public static String generatePasswoed(int requiredLength, Random random, String... sequence) {
		String[] passwordSequence = sequence;
		if (passwordSequence == null || passwordSequence.length == 0) {
			passwordSequence = getSequence(PasswordType.ALPHANUMERIC_SYMBOL);
		}
		if (requiredLength < 0) {
			throw new IllegalArgumentException("Requested random Character resource length " + requiredLength + " is less than 0.");
		} else if (requiredLength < MIN_CHARACTERS_ALLOWED) {
			throw new IllegalArgumentException("Requested random Character resource length " + requiredLength + " is less than "
					+ MIN_CHARACTERS_ALLOWED + " (minimum Character allowed).");
		} else if (requiredLength < passwordSequence.length) {
			throw new IllegalArgumentException("Requested random Character resource length " + requiredLength + " is less than "
					+ passwordSequence.length + " (sequence array length).");
		}

		StringBuilder sb = new StringBuilder(requiredLength);

		Map<Integer, Character> index2characterMap = getStrongPasswordMap(requiredLength, random, passwordSequence);
		
		for (int index = 0; index < requiredLength; ++index) {
			if (index2characterMap.containsKey(index)) {
				sb.append(index2characterMap.get(index));
			} else {
				int sequenceIndex = (int) Math.round(Math.floor(random.nextDouble() * passwordSequence.length));
				sb.append(randomCharacter(passwordSequence[sequenceIndex].toCharArray(), random));
			}
		}
		return sb.toString();
	}
	
	/**
	 * @param requiredLength
	 * @param random
	 * @param sequence
	 * @return
	 */
	private static Map<Integer, Character> getStrongPasswordMap(int requiredLength, Random random, String... sequence) {
		Map<Integer, Character> index2characterMap = new HashMap<Integer, Character>();
		while (index2characterMap.size() < sequence.length) {
			int sequenceIndex = index2characterMap.size();
			int index = (int) Math.round(Math.floor(random.nextDouble() * requiredLength));
			if (!index2characterMap.containsKey(index)) {
				index2characterMap.put(index, randomCharacter(sequence[sequenceIndex].toCharArray(), random));
			}
		}
		return index2characterMap;
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
