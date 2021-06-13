package org.serratec.viroumemeapi.exceptions;

public class ProductStockLessThanRequestedException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductStockLessThanRequestedException() {
		super();
	}

	public ProductStockLessThanRequestedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductStockLessThanRequestedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductStockLessThanRequestedException(String message) {
		super(message);
	}

	public ProductStockLessThanRequestedException(Throwable cause) {
		super(cause);
	}
}
