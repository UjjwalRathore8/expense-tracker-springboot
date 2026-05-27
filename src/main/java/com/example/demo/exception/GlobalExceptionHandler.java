package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(
	        MethodArgumentNotValidException ex)
	{
	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errors.put(error.getField(), error.getDefaultMessage());
	    });

	    return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handleInvalidCredentials(
	        InvalidCredentialsException ex)
	{
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(ex.getMessage());
	}
	
	
}
