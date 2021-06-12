package org.serratec.viroumemeapi.exceptions;

public class CpfNotEditableException extends Exception {

	private static final long serialVersionUID = 1L;

	public CpfNotEditableException() {
		super();
	}

	public CpfNotEditableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CpfNotEditableException(String message, Throwable cause) {
		super(message, cause);
	}

	public CpfNotEditableException(String message) {
		super(message);
	}

	public CpfNotEditableException(Throwable cause) {
		super(cause);
	}
}
