package com.audit.services.impl;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.audit.services.AuthProvider;
import com.audit.services.UserDetailService;
import com.utilities.property.AppProperties;

import lombok.AllArgsConstructor;

/**
 * Implementation class for the {@link com.audit.services.AuthProvider}
 * interface. This class provides the authentication logic used in the Spring
 * Security configuration to validate and authenticate users based on their
 * credentials.
 * 
 * <p>
 * The class makes use of {@link DaoAuthenticationProvider} to handle the
 * authentication process by integrating with a {@code UserDetailService} for
 * fetching user details from the database and {@code BCryptPasswordEncoder} for
 * encoding and validating passwords.
 * </p>
 * 
 * The {@link DaoAuthenticationProvider} is configured with:
 * <ul>
 * <li>A custom {@code UserDetailService} implementation to retrieve user
 * details.</li>
 * <li>A {@code BCryptPasswordEncoder} instance with a configurable strength for
 * secure password encoding.</li>
 * </ul>
 * 
 * <p>
 * This implementation ensures adherence to Spring Security's best practices for
 * handling user authentication, including secure password management.
 * </p>
 * 
 * @see <a href=
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html">Service
 *      Annotation</a>
 * @see <a href=
 *      "https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/authentication/dao/DaoAuthenticationProvider.html">DaoAuthenticationProvider</a>
 * @see <a href=
 *      "https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html">BCryptPasswordEncoder</a>
 * @see com.audit.services.AuthProvider
 * @see com.audit.services.UserDetailService
 * @author Kabir Akware
 */
@Service
@AllArgsConstructor
public class AuthProviderImpl implements AuthProvider {

	/**
	 * {@code UserDetailService} interface variable
	 */
	private UserDetailService encryptionUserDetailsService;

	/**
	 * Method to provide implementation for a custom encryption logic to encode and
	 * decode passwords using {@code DaoAuthenticationProvider} implementing the
	 * logic to fetch user data from data base for authentication
	 */
	@Override
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(encryptionUserDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(AppProperties.intProperty("bcrypt.strength")));
		return provider;
	}

}
