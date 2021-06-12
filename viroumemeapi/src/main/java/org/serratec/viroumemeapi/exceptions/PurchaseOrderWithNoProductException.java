package org.serratec.viroumemeapi.exceptions;

public class PurchaseOrderWithNoProductException extends Exception {

	private static final long serialVersionUID = 1L;

	public PurchaseOrderWithNoProductException() {
		super();
	}

	public PurchaseOrderWithNoProductException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PurchaseOrderWithNoProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public PurchaseOrderWithNoProductException(String message) {
		super(message);
	}

	public PurchaseOrderWithNoProductException(Throwable cause) {
		super(cause);
	}
}
