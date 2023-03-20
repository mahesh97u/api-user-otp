 package com.us.constants;

public enum ErrorConstants {

	SUCCESS("200"),
	UNAUTHORIZED("401"),
	FORBIDDEN("403"),
	INVALID("300"),
	NOT_FOUND("404"),
	BAD_REQUEST("400"),
	CREATED("201"),
	INTERNAL_SERVER_ERROR("500"),
	REQUIRED_FIELD_EMPTY("601"),
	UNIQUE_KEY_EXISTS("602"),
	PASSWORD_MISMATCHED("603"),
	USER_NOT_FOUND("604"),
	TOKEN_EXPIRED("605"),
	SERVER_ERROR("Could not perform the request");
	
	private String displayName;

	ErrorConstants(String displayName) {
        this.displayName = displayName;
    }
	
	@Override 
	public String toString() { return displayName; }
}
