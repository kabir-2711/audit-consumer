package com.audit.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.audit.exception.AppException;
import com.model.dto.UserDto;
import com.model.entity.Users;

/**
 * This Interface defines the contract for {@code UserDetailService} service.
 * The service provides functionalities for registering and fetching user
 * details from data base.
 * 
 * <p>
 * This interface extends {@link UserDetailsService} to implement the inherited
 * method to load and authenticate the user details for the user name.
 * </p>
 * 
 * Features:
 * 
 * <ul>
 * <li>Fetch the user data from the data base.</li>
 * <li>Add or update a user in the data base.</li>
 * <li>Provides methods for querying and retrieving user details.</li>
 * </ul>
 * 
 * <p>
 * The implementation of this service can be used to track database activities
 * for security, debugging, or data integrity purposes. It can be invoked from
 * service or repository layers.
 * </p>
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/core/userdetails/UserDetailsService.html">
 *      UserDetailsService </a>
 * @author Kabir Akware
 */
public interface UserDetailService extends UserDetailsService {

	/**
	 * Declaration of {@code register} to register a user in the system
	 * 
	 * @param user {@link UserDto} object containing user information
	 * @return {@link UserDto} with user details given by the user
	 * @throws AppException Thrown when a custom exception occurs
	 */
	UserDto register(UserDto user) throws AppException;

	/**
	 * Declaration of {@code register} to get all users in the system
	 * 
	 * @return {@link UserDto} with user details given by the user
	 * @throws AppException 
	 */
	List<Users> getUsers() throws AppException;

}
