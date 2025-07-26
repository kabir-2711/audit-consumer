/**
 * This package contains the Custom exception classes responsible for handling
 * the exceptions thrown in the {@code encryption} application.
 * 
 * <p>
 * The exceptions in this package are designed to provide meaningful error
 * messages and handle various error scenarios through the application.
 * </p>
 * 
 * <h2>Components</h2>
 * <ul>
 * <li>{@link com.audit.exception.AppException} - Thrown when a specific
 * application rule is violated</li>
 * <li>{@link com.encryption.exception.AppRTException} - Thrown when a custom
 * Runtime exception needs to be thrown in the application</li>
 * <li>{@link com.utilities.exceptions.ConfigException} - Thrown when a
 * configurations are failed to load at application startup</li>
 * <li>{@link com.audit.exception.UserNameNotFound} - Thrown when a user is
 * not found in <code>SecurityConfig</code>></li>
 * </ul>
 * 
 * <p>
 * Each custom exception in this package extends
 * {@link java.lang.RuntimeException} and includes detailed error messages for
 * better troubleshooting.
 * </p>
 * 
 * 
 * @author Kabir Akware
 */
package com.audit.exception;