package org.serratec.viroumemeapi.util;

import org.serratec.viroumemeapi.exceptions.AddressNotAssociatedWithClientException;
import org.serratec.viroumemeapi.exceptions.CategoryReferencedByProductException;
import org.serratec.viroumemeapi.exceptions.CpfNotEditableException;
import org.serratec.viroumemeapi.exceptions.ItemAlreadyExistsException;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.exceptions.ProductStockLessThanRequestedException;
import org.serratec.viroumemeapi.exceptions.PurchaseOrderNotEditableException;
import org.serratec.viroumemeapi.exceptions.PurchaseOrderWithNoProductException;
import org.serratec.viroumemeapi.exceptions.QuantityCannotBeZeroException;
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
	public ResponseEntity<String> handlePurchaseOrderWithNoProductException(
			PurchaseOrderWithNoProductException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(CategoryReferencedByProductException.class)
	public ResponseEntity<String> handleCategoryReferencedByProductException(
			CategoryReferencedByProductException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(ProductStockLessThanRequestedException.class)
	public ResponseEntity<String> handleProductStockLessThanRequestedException(
			ProductStockLessThanRequestedException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(QuantityCannotBeZeroException.class)
	public ResponseEntity<String> handleQuantityCannotBeZeroException(QuantityCannotBeZeroException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(AddressNotAssociatedWithClientException.class)
	public ResponseEntity<String> handleAddressNotAssociatedWithClientException(
			AddressNotAssociatedWithClientException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<String> handleItemAlreadyExistsException(ItemAlreadyExistsException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}
}
