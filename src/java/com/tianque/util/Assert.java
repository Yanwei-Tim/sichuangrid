package com.tianque.util;

import java.util.Collection;
import java.util.Map;

public final class Assert {

	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	public static void notEmpty(String text, String message) {
		if (text == null || text.trim().length() == 0) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Object[] array, String message) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Collection<?> collection, String message) {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Map<?, ?> map, String message) {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	// ==============================================================================================================
	
	public static void isTrue(boolean expression, RuntimeException exception) {
		if (!expression) {
			throw exception;
		}
	}

	public static void isFalse(boolean expression, RuntimeException exception) {
		if (expression) {
			throw exception;
		}
	}

	public static void isNull(Object object, RuntimeException exception) {
		if (object != null) {
			throw exception;
		}
	}

	public static void notNull(Object object, RuntimeException exception) {
		if (object == null) {
			throw exception;
		}
	}

	public static void noNullElements(Object[] array, RuntimeException exception) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					throw exception;
				}
			}
		}
	}

	public static void notEmpty(String text, RuntimeException exception) {
		if (text == null || text.trim().length() == 0) {
			throw exception;
		}
	}

	public static void notEmpty(Object[] array, RuntimeException exception) {
		if (array == null || array.length == 0) {
			throw exception;
		}
	}

	public static void notEmpty(Collection<?> collection, RuntimeException exception) {
		if (collection == null || collection.isEmpty()) {
			throw exception;
		}
	}

	public static void notEmpty(Map<?, ?> map, RuntimeException exception) {
		if (map == null || map.isEmpty()) {
			throw exception;
		}
	}

	@SuppressWarnings("unused")
	private static void isInstanceOf(Class type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(message + "Object of class [" + (obj != null ? obj.getClass().getName() : "null")
					+ "] must be an instance of " + type);
		}
	}

	@SuppressWarnings("unused")
	private static void isAssignable(Class superType, Class subType, String message) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
		}
	}

}
