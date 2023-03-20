package com.us.security;

public class SecurityConstants {

	public static final String SECRET = "jwtTokenKey";

	public static final String[] GET_ALLOWED_URL = { "/v1/user/dropdown", "/v1/file/**" };
	
	public static final String[] ALLOWED_URL = { "/v1/user" ,"/v1/user/login", "/v1/user/forgot-password", "/v1/user/reset-password" };

}
