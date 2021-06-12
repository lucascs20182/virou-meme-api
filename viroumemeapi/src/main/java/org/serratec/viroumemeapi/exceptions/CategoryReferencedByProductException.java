package org.serratec.viroumemeapi.exceptions;

public class CategoryReferencedByProductException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoryReferencedByProductException() {
		super();
	}

	public CategoryReferencedByProductException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CategoryReferencedByProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoryReferencedByProductException(String message) {
		super(message);
	}

	public CategoryReferencedByProductException(Throwable cause) {
		super(cause);
	}
}
