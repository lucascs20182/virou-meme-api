package org.serratec.viroumemeapi.exceptions;

public class PurchaseOrderNotEditableException extends Exception {

	private static final long serialVersionUID = 1L;

	public PurchaseOrderNotEditableException() {
		super();
	}

	public PurchaseOrderNotEditableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PurchaseOrderNotEditableException(String message, Throwable cause) {
		super(message, cause);
	}

	public PurchaseOrderNotEditableException(String message) {
		super(message);
	}

	public PurchaseOrderNotEditableException(Throwable cause) {
		super(cause);
	}
}
