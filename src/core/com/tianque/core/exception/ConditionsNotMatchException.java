package com.tianque.core.exception;

public class ConditionsNotMatchException extends RuntimeException {
	public ConditionsNotMatchException() {
		super();
	}

	public ConditionsNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConditionsNotMatchException(String message) {
		super(message);
	}

	public ConditionsNotMatchException(Throwable cause) {
		super(cause);
	}
}
