package org.jsp.merchantbootapp.exception;

public class InvalidCredentialsException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Invalid id or email or phone or Password";
	}
}
