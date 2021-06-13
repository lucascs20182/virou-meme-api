package org.serratec.viroumemeapi.exceptions;

public class AddressNotAssociatedWithClientException extends Exception {

	private static final long serialVersionUID = 1L;

	public AddressNotAssociatedWithClientException() {
		super();
	}

	public AddressNotAssociatedWithClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressNotAssociatedWithClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressNotAssociatedWithClientException(String message) {
		super(message);
	}

	public AddressNotAssociatedWithClientException(Throwable cause) {
		super(cause);
	}
}
