package com.audit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Custom Exception Class extending {@link RuntimeException} class thrown to
 * specify and handle when the User name is not found in the application and
 * display it in a user friendly way
 * 
 * 
 * @author Kabir Akware
 */
@Getter
@AllArgsConstructor
public class UserNameNotFound extends RuntimeException {
	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable to store custom error message
	 */
	private String message;

	/**
	 * Method to get a new instance of {@link UserNameNotFound}
	 * 
	 * @param message Custom error message
	 * @return New instance of {@link UserNameNotFound}
	 */
	public static UserNameNotFound getInstance(String message) {
		return new UserNameNotFound(message);
	}

}
