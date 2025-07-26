/**
 * This package contains the controllers responsible for handling HTTP requests
 * in the {@code encryption} application.
 * 
 * <p>
 * Each controller is responsible for managing specific end points and
 * processing the incoming requests, delegating the necessary actions to
 * services, and returning appropriate responses.
 * 
 * <h2>Components</h2> The main controllers in this package include:
 * <ul>
 * <li>{@link com.audit.v1.controllers.AuditController} - Handles
 * encryption/decryption related actions</li>
 * <li>{@link com.encryption.v1.controllers.UserController} - Handles user related
 * actions</li>
 * </ul>
 * 
 * <p>
 * Controllers in this package follow RESTful principles and interact with
 * various service layers to manage the application data. They use annotations
 * like {@link org.springframework.web.bind.annotation.RestController} and
 * {@link org.springframework.web.bind.annotation.RequestMapping} to map HTTP
 * requests to handler methods.
 * 
 * <h2>Example usage:</h2>
 * 
 * <pre>
 *	// GET request to fetch CSRF token
 * 		GET /token/csrf-token
 * 
 * 	// POST requests to encrypt and decrypt the given data
 * 		POST /enc-service/encrypt and enc-service/decrypt
 * </pre>
 * 
 * 
 * @author Kabir Akware
 */
package com.audit.v1.controllers;