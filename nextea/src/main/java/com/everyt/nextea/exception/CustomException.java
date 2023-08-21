package com.everyt.nextea.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
	
	private static final long serialVersionUID = -8969432388861282126L;
	ErrorCode errorCode;
}