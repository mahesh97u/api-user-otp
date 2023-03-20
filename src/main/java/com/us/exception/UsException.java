package com.us.exception;

public class UsException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9137668219535646340L;

	private String errorCode;

	private String message;

	private String uniqueNo;
	
	public UsException(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public UsException(String errorCode, String message, String uniqueNo) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.uniqueNo = uniqueNo;
	}

	public String getUniqueNo() {
		return uniqueNo;
	}

	public void setUniqueNo(String uniqueNo) {
		this.uniqueNo = uniqueNo;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
