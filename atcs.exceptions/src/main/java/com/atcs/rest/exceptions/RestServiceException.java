package com.atcs.rest.exceptions;

import com.atcs.rest.exceptions.code.GenericErrorCode;

@SuppressWarnings("serial")
public class RestServiceException extends RuntimeException {

    private String errorCode;
    private int status;

    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public RestServiceException(GenericErrorCode error, String message) {
        super(message);
        this.errorCode = error.name();
        this.status = error.getStatus();
    }
    
	
}