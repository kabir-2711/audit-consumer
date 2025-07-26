package com.audit.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.model.entity.Users;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Custom implementation of Spring Security's <a href =
 * "https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UserDetails.html">
 * UserDetails </a> interface.
 *
 * This class represents a user entity for authentication purposes. It provides
 * the necessary details about the user such as user name, password and roles
 * required by Spring Security for authentication and authorization.
 * 
 * This class is typically used in conjunction with {@code UserDetailsService}
 * to load user-specific data during the authentication process.
 *
 * <p>
 * It overrides methods to return the user authorities (roles) and credentials.
 * </p>
 *
 * Example:
 * 
 * <pre>
 * {@code
 * UserPricipal userDetails = new UserPricipal(user);
 * String user = userDetails.getUsername();
 * }
 * </pre>
 *
 *
 * @see <a href =
 *      "https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UserDetails.html">
 *      UserDetails </a>
 * @author Kabir Akware
 */
@AllArgsConstructor
@ToString
public class UserPricipal implements UserDetails {

	/**
	 * {@link Users} details model object
	 */
	private Users users;

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -9049447323951589892L;

	/**
	 * Method to get a collection of authority from {@link Users}
	 * 
	 * @return Collection of Authorities for the user
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(() -> users.getAuthorities());
	}

	/**
	 * Method to get the password from {@link Users}
	 * 
	 * @return Password for the user
	 */
	@Override
	public String getPassword() {
		return users.getPassword();
	}

	/**
	 * Method to get the user name from {@link Users}
	 * 
	 * @return User name for the user
	 */
	@Override
	public String getUsername() {
		return users.getUserName();
	}

	/**
	 * Method to get a new instance of {@code UserPricipal} class
	 * 
	 * @param users {@code User} object
	 * @return New instance of {@code UserPricipal} class
	 */
	public static UserPricipal getInstance(Users users) {
		return new UserPricipal(users);
	}

}
