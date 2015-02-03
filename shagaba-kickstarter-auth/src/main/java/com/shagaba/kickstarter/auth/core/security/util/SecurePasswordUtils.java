package com.shagaba.kickstarter.auth.core.security.util;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SecurePasswordUtils {

	public static final int MIN_CHARACTERS_ALLOWED = 6;

	/**
	 * @param requiredLength
	 * @param passwordTypeEnum
	 * @return
	 */
	public static String generatePasswoed(int requiredLength, PasswordTypeEnum passwordTypeEnum) {
		return generatePasswoed(requiredLength, passwordTypeEnum.getSequence());
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
			passwordSequence = PasswordTypeEnum.ALPHANUMERIC_SYMBOL.getSequence();
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
