/**
 * This package provides the service interfaces for the {@code encryption}
 * application.
 * 
 * <p>
 * These service interfaces define the business logic operations that can be
 * performed on the request like encryption and decryption. The implementation
 * of these services interacts with
 * {@link com.encryption.utility.EncryptionUtility} class to perform encryption
 * and decryption operations
 * 
 * 
 * <h2>Components</h2>
 * <ul>
 * <li>{@link com.encryption.services.impl.EncryptionServiceImpl} - Implements
 * logic to handle encryption and decryption related logic.</li>
 * <li>{@link com.audit.services.impl.AuditServiceImpl} - Implements logic
 * to handle auditing request and responses.</li>
 * <li>{@link com.audit.services.impl.UserDetailServiceImpl} - Implements
 * logic to provide the user details present in data base.</li>
 * </ul>
 * 
 * <p>
 * The service interfaces follow the dependency inversion principle, allowing
 * for easy testing and implementation changes.
 * 
 * 
 * @author Kabir Akware
 */
package com.audit.services.impl;