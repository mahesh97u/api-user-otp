package com.us.bo;

import java.io.Serializable;
import java.util.UUID;

public class UserBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2827289238775015274L;

	private UUID userId;
	
	private String email;
	
	private String password;
	
	private String newPassword;
	
	private String otp;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
