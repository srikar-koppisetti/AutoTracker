package com.srikar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class VehicleBadRequestException extends RuntimeException {
	public VehicleBadRequestException(String message){
		super(message);
	}
	public VehicleBadRequestException(String message, Throwable cause){
		super(message,cause);
	}
}
