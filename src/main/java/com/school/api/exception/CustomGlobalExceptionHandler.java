package com.school.api.exception;

import java.util.Date;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"from MethoNotValid Exception in GEH", ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	// HttpRequestMethodNotSupported
	// this method will be called when we use wrong uri
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"from handleHttpRequestMethodNotSupported Exception in GEH- method not allowed", ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);

	}
	
	//UserNameNotFoundException
	@ExceptionHandler(UserNameNotFoundExcepton.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNameNotFoundExcepton ex, 
			WebRequest request){
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
	
	//getStudentById method in CorollerClass throws ConstraintViolation Exception When user gives PathVariable lessthan 1
	//so we are going to handle that in CustomGlobalExceptionHandler class
	//ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

}
