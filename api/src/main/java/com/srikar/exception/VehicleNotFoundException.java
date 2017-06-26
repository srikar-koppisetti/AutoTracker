package com.srikar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends RuntimeException {
	public VehicleNotFoundException(String message){
		super(message);
	}
	
	public VehicleNotFoundException(String message, Throwable cause){
		super(message,cause);
	}
}
