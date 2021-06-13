package org.serratec.viroumemeapi.exceptions;

public class ItemAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ItemAlreadyExistsException() {
		super();
	}

	public ItemAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ItemAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemAlreadyExistsException(String message) {
		super(message);
	}

	public ItemAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
