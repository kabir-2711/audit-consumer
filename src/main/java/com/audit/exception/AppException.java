package com.audit.exception;

import org.springframework.http.HttpStatus;

import com.model.enums.Codes;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Custom Exception Class extending {@link Exception} class thrown to specify
 * and handle the Exceptions in the application and display it in a user
 * friendly way
 * 
 * 
 * @author Kabir Akware
 */
@Getter
@AllArgsConstructor
public class AppException extends Exception {
	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable to store error code
	 */
	private Codes code;

	/**
	 * Variable to store custom error message
	 */
	private String message;

	/**
	 * Variable to store error description
	 */
	private String description;

	/**
	 * Variable to store HTTP status value
	 */
	private HttpStatus status;

	/**
	 * Method to get a new instance of {@link AppException}
	 * 
	 * @param message     Custom error message
	 * @param description Error description
	 * @param code        Custom error code
	 * @param status      HTTP error status
	 * @return New instance of {@link AppException}
	 */
	public static AppException getInstance(String message, String description, Codes code, HttpStatus status) {
		return new AppException(code, message, description, status);
	}
}
