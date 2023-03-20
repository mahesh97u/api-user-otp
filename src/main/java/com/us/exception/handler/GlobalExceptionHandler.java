package com.us.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.us.bo.Response;
import com.us.constants.ErrorConstants;



@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globalExceptionHandler(Exception ex) {
		ex.printStackTrace();
		Response response = new Response();
		response.setStatus(ErrorConstants.INVALID.toString());
		response.setMessage("Internal Server Error");
		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
	}
}
