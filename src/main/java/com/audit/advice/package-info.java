/**
 * This package contains the Custom exception classes responsible for handling
 * the exceptions globally thrown in the {@code encryption} application and
 * Aspect components which handle logging operations in the application..
 * 
 * <p>
 * Classes in this package use Spring's {@code @ControllerAdvice} and
 * {@code @RestControllerAdvice} annotations to provide centralized exception
 * handling for all controllers. This ensures consistent error responses
 * throughout the application and reduces boilerplate exception handling code in
 * individual controllers.
 * </p>
 * 
 * <h2>Components</h2>
 * 
 * <ul>
 * <li>{@link com.encryption.advice.AuditControllerAdvice} - Handles
 * exceptions thrown in the system globally and returns a response wrapped in
 * {@link com.model.dto.AppError} object</li>
 * <li>{@link com.audit.advice.LoggingAspect} - Handles the logging in the
 * application</li>
 * </ul>
 * <p>
 * Key responsibilities of classes in this package include:
 * </p>
 * <ul>
 * <li>Catching validation errors, such as those related to invalid or missing
 * request body fields.</li>
 * <li>Handling common exceptions like
 * {@link org.springframework.web.bind.MethodArgumentNotValidException},
 * {@link org.springframework.http.converter.HttpMessageNotReadableException},
 * and others.</li>
 * <li>Providing meaningful and consistent error responses, including HTTP
 * status codes and error messages.</li>
 * <li>Logging the entry, exit, errors and returns of a method in the
 * application</li>
 * </ul>
 * 
 * <p>
 * The error responses returned by the classes in this package typically follow
 * a consistent structure wrapping in {@link com.model.dto.AppError} model class
 * </p>
 * 
 * 
 * @author Kabir Akware
 */
package com.audit.advice;
