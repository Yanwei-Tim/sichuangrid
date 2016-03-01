package com.tianque.issue.state.exception;

import com.tianque.exception.base.BusinessValidationException;

public class UnsupportOperationException extends BusinessValidationException {

	public UnsupportOperationException() {
		super();
	}

	// public UnsupportOperationException(String msg, Throwable cause) {
	// super(msg, cause);
	// }

	public UnsupportOperationException(String msg) {
		super(msg);
	}

	// public UnsupportOperationException(Throwable cause) {
	// super(cause);
	// }
}
