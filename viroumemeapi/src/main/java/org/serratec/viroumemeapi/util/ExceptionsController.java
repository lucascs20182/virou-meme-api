package org.serratec.viroumemeapi.util;

import org.serratec.viroumemeapi.exceptions.CpfNotEditableException;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.exceptions.PurchaseOrderNotEditableException;
import org.serratec.viroumemeapi.exceptions.PurchaseOrderWithNoProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(CpfNotEditableException.class)
	public ResponseEntity<String> handleCpfNotEditableException(CpfNotEditableException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(PurchaseOrderNotEditableException.class)
	public ResponseEntity<String> handlePurchaseOrderNotEditableException(PurchaseOrderNotEditableException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}
	
	@ExceptionHandler(PurchaseOrderWithNoProductException.class)
	public ResponseEntity<String> handlePurchaseOrderWithNoProductExceptionn(PurchaseOrderWithNoProductException exception) {
		return ResponseEntity.ok().header("x-erro-msg", exception.getMessage()).build();
	}
}
