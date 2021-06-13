package org.serratec.viroumemeapi.exceptions;

public class QuantityCannotBeZeroException extends Exception {

	private static final long serialVersionUID = 1L;

	public QuantityCannotBeZeroException() {
		super();
	}

	public QuantityCannotBeZeroException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QuantityCannotBeZeroException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuantityCannotBeZeroException(String message) {
		super(message);
	}

	public QuantityCannotBeZeroException(Throwable cause) {
		super(cause);
	}

}
