package com.audit.services;

import org.springframework.security.authentication.AuthenticationProvider;

/**
 * Interface defining the contract for providing custom authentication logic
 * used in Spring Security configuration.
 * 
 * <p>
 * This interface allows implementations to define how authentication providers,
 * such as
 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider},
 * are configured and supplied. Implementations can encapsulate custom logic for
 * password encoding, user data retrieval, and authentication validation.
 * </p>
 * 
 * <p>
 * The primary goal of this interface is to decouple the authentication
 * configuration from the core Spring Security framework, making the
 * implementation extensible and customizable for specific use cases, such as
 * database-backed user authentication.
 * </p>
 * 
 * <h2>Usage</h2>
 * <ul>
 * <li>The implementing class is responsible for creating and configuring an
 * instance of {@link AuthenticationProvider}.</li>
 * <li>It is typically used in Spring Security configuration to integrate
 * authentication logic into the security filter chain.</li>
 * </ul>
 * 
 * @see <a href=
 *      "https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/authentication/AuthenticationProvider.html">
 *      AuthenticationProvider</a>
 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider
 * @see org.springframework.security.crypto.password.PasswordEncoder
 * @author Kabir Akware
 */
public interface AuthProvider {

	/**
	 * Provides an instance of {@link AuthenticationProvider} with custom
	 * authentication logic.
	 * 
	 * Implementations of this method typically configure an
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}
	 * or a similar provider to handle the authentication process. The configuration
	 * may include:
	 * <ul>
	 * <li>A {@link org.springframework.security.crypto.password.PasswordEncoder}
	 * for encoding and validating passwords.</li>
	 * <li>A custom implementation of {@code UserDetailsService} for fetching user
	 * details from a database or another source.</li>
	 * </ul>
	 * 
	 * @return an instance of {@link AuthenticationProvider} configured with custom
	 *         authentication logic.
	 */
	AuthenticationProvider authenticationProvider();
}
