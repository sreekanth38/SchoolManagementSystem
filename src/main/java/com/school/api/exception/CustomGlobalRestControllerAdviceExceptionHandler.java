package com.school.api.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class CustomGlobalRestControllerAdviceExceptionHandler {
	
	//Exception handling for getStudentByUserName method in StudentController
	@ExceptionHandler(UserNameNotFoundExcepton.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails userNameNotFound(UserNameNotFoundExcepton ex) {
		return new CustomErrorDetails(new Date(), "From @RestControllerAdvice Not Found", ex.getMessage());
	}
}
