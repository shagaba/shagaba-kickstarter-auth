package com.shagaba.kickstarter.auth.core.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class RandomObjectFiller {
	private static final int MAX_COPIES = 3;
	public static final String CHARS = "123467890.abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final Random RANDOM = new Random();

	public static <T> T randomObject(Class<T> objectClass) {
		return randomObject(objectClass, null);
	}

	public static <T, V> T randomObject(Class<T> objectClass, Class<V> typeClass1) {
		return randomObject(objectClass, typeClass1, null);
	}

	public static <T, V, W> T randomObject(Class<T> objectClass, Class<V> typeClass1, Class<W> typeClass2) {
		T object = null;
		try {
			if (boolean.class.isAssignableFrom(objectClass) || Boolean.class.isAssignableFrom(objectClass)) {
				object = (T) randomBoolean();
			} else if (byte.class.isAssignableFrom(objectClass) || Byte.class.isAssignableFrom(objectClass)) {
				object = (T) randomByte();
			} else if (short.class.isAssignableFrom(objectClass) || Short.class.isAssignableFrom(objectClass)) {
				object = (T) randomShort();
			} else if (int.class.isAssignableFrom(objectClass) || Integer.class.isAssignableFrom(objectClass)) {
				object = (T) randomInt();
			} else if (long.class.isAssignableFrom(objectClass) || Long.class.isAssignableFrom(objectClass)) {
				object = (T) randomLong();
			} else if (BigInteger.class.isAssignableFrom(objectClass)) {
				object = (T) randomBigInteger();
			} else if (float.class.isAssignableFrom(objectClass) || Float.class.isAssignableFrom(objectClass)) {
				object = (T) randomFloat();
			} else if (double.class.isAssignableFrom(objectClass) || Double.class.isAssignableFrom(objectClass)) {
				object = (T) randomDouble();
			} else if (BigDecimal.class.isAssignableFrom(objectClass)) {
				object = (T) randomBigDecimal();
			} else if (char.class.isAssignableFrom(objectClass) || Character.class.isAssignableFrom(objectClass)) {
				object = (T) randomChar();
			} else if (String.class.isAssignableFrom(objectClass)) {
				object = (T) randomString();
			} else if (UUID.class.isAssignableFrom(objectClass)) {
				object = (T) randomUuid();
			} else if (Date.class.isAssignableFrom(objectClass)) {
				object = (T) randomDate();
			} else if (Calendar.class.isAssignableFrom(objectClass)) {
				object = (T) randomCalendar();
			} else if (Map.class.isAssignableFrom(objectClass)) {
				object = (T) randomMap((Class<Map>) objectClass, typeClass1, typeClass2);
			} else if (List.class.isAssignableFrom(objectClass)) {
				object = (T) randomList((Class<List>) objectClass, typeClass1);
			} else if (Set.class.isAssignableFrom(objectClass)) {
				object = (T) randomSet((Class<Set>) objectClass, typeClass1);
			} else if (Collection.class.isAssignableFrom(objectClass)) {
				object = (T) randomCollection((Class<Collection>) objectClass, typeClass1);
			} else if (objectClass.isArray()) {
				object = (T) randomArray(objectClass.getComponentType());
			} else if (objectClass.isEnum()) {
				object = (T) randomEnum(objectClass);
			} else {
				object = objectClass.newInstance();
				for (Field field : objectClass.getDeclaredFields()) {
					if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
						Class fieldType = field.getType();
						field.setAccessible(true);
						if (field.getGenericType() instanceof ParameterizedType) {
							ParameterizedType paramType = (ParameterizedType) field.getGenericType();
							Type[] typeArgs = paramType.getActualTypeArguments();
							if (typeArgs.length == 1) {
								if (typeArgs[0] instanceof ParameterizedType) {
									// TODO: HANDLE NESTED PARAM TYPE
								} else {
									field.set(object, randomObject(fieldType, (Class<?>) typeArgs[0]));
								}
							} else if (typeArgs.length == 2) {
								field.set(object, randomObject(fieldType, (Class<?>) typeArgs[0], (Class<?>) typeArgs[1]));
							}
						} else {
							field.set(object, randomObject(fieldType));
						}
					}
				}
			}
		} catch (Exception ex) {
			// LOG.error("Exception while building the random object", ex);
		}
		return object;
	}

	public static Boolean randomBoolean() {
		return RANDOM.nextBoolean();
	}

	public static Byte randomByte() {
		return (byte) (Byte.MIN_VALUE + RANDOM.nextInt((int) Byte.MAX_VALUE - Byte.MIN_VALUE));
	}

	public static Short randomShort() {
		return (short) (Short.MIN_VALUE + RANDOM.nextInt(Short.MAX_VALUE - Short.MIN_VALUE));
	}

	public static Integer randomInt() {
		return RANDOM.nextInt();
	}

	public static Long randomLong() {
		return RANDOM.nextLong();
	}

	public static BigInteger randomBigInteger() {
		return BigInteger.valueOf(randomLong()).multiply(BigInteger.valueOf(randomLong()));
	}

	public static Float randomFloat() {
		return RANDOM.nextFloat();
	}

	public static Double randomDouble() {
		return RANDOM.nextDouble();
	}

	public static BigDecimal randomBigDecimal() {
		return BigDecimal.valueOf(randomDouble()).multiply(BigDecimal.valueOf(randomDouble()));
	}

	public static Character randomChar() {
		return CHARS.charAt(RANDOM.nextInt(CHARS.length()));
	}

	public static String randomString() {
		int length = 20 + RANDOM.nextInt(20);
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(randomChar());
		}
		return sb.toString();
	}

	public static UUID randomUuid() {
		return UUID.randomUUID();
	}

	public static Date randomDate() {
		return randomCalendar().getTime();
	}

	public static Calendar randomCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1990 + RANDOM.nextInt(100), RANDOM.nextInt(12), 1 + RANDOM.nextInt(28), RANDOM.nextInt(24), RANDOM.nextInt(60),
				RANDOM.nextInt(60));
		return calendar;
	}

	public static <T extends Map<V, W>, V, W> T randomMap(Class<T> mapClass, Class<V> keyClass, Class<W> valueClass) {
		T mapObject = null;
		try {
			if (mapClass.isInterface())
				mapObject = mapClass.newInstance();
			else
				mapObject = (T) new HashMap<>();
			for (int i = 0; i <= RANDOM.nextInt(MAX_COPIES); i++) {
				mapObject.put(randomObject(keyClass), randomObject(valueClass));
			}
		} catch (Exception ex) {
			// LOG.error("Exception while building the random map", ex);
		}
		return mapObject;
	}

	public static <T extends List<V>, V> T randomList(Class<T> listClass, Class<V> valueClass) {
		T listObject = null;
		try {
			if (listClass.isInterface())
				listObject = (T) new ArrayList<>();
			else
				listObject = listClass.newInstance();
			for (int i = 0; i <= RANDOM.nextInt(MAX_COPIES); i++) {
				listObject.add(randomObject(valueClass));
			}
		} catch (Exception ex) {
			// LOG.error("Exception while building the random list", ex);
		}
		return listObject;
	}

	public static <T extends Set<V>, V> T randomSet(Class<T> setClass, Class<V> valueClass) {
		T setObject = null;
		try {
			if (setClass.isInterface())
				setObject = (T) new HashMap<>();
			else
				setObject = setClass.newInstance();
			for (int i = 0; i <= RANDOM.nextInt(MAX_COPIES); i++) {
				setObject.add(randomObject(valueClass));
			}
		} catch (Exception ex) {
			// LOG.error("Exception while building the random set", ex);
		}
		return setObject;
	}

	public static <T extends Collection<V>, V> T randomCollection(Class<T> collectionClass, Class<V> valueClass) {
		T collectionObject = null;
		try {
			if (collectionClass.isInterface())
				collectionObject = (T) new ArrayList<>();
			else
				collectionObject = collectionClass.newInstance();
			for (int i = 0; i <= RANDOM.nextInt(MAX_COPIES); i++) {
				collectionObject.add(randomObject(valueClass));
			}
		} catch (Exception ex) {
			// LOG.error("Exception while building the random collection", ex);
		}
		return collectionObject;
	}

	public static <T> T randomEnum(Class<T> enumClass) {
		T[] enums = enumClass.getEnumConstants();
		return enums[RANDOM.nextInt(enums.length)];
	}

	public static <T> T[] randomArray(Class<T> arrayClass) {
		List<T> arrayList = new ArrayList<>();
		for (int i = 0; i <= RANDOM.nextInt(MAX_COPIES); i++) {
			arrayList.add(randomObject(arrayClass));
		}
		return (T[]) arrayList.toArray();
	}

}
