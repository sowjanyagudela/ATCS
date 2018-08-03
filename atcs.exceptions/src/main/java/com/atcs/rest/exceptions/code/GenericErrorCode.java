package com.atcs.rest.exceptions.code;

public enum GenericErrorCode {
	
		SAME_AIRCRAFT_EXISTS       (200),
		HIGHER_PRIORITY_AIRCRAFT_EXISTS(200),
		AIRCRAFT_DOESNT_EXISTS(200),
		INVALID_AIRCRAFT_TYPE(200),
		INVALID_AIRCRAFT_SIZE(200);


	private final int status;


	GenericErrorCode(int status) {
		this.status = status;
	}
	

	public int getStatus() {
		return status;
	}
}
