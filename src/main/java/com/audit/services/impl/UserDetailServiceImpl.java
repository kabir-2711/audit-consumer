package com.audit.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.audit.config.UserPricipal;
import com.audit.exception.AppException;
import com.audit.exception.UserNameNotFound;
import com.audit.repo.UserDetailsRepo;
import com.audit.services.UserDetailService;
import com.audit.utility.CommonUtility;
import com.model.dto.UserDto;
import com.model.entity.Users;
import com.model.enums.Codes;
import com.utilities.property.AppProperties;

import lombok.AllArgsConstructor;

/**
 * This class provides the implementation of {@link UserDetailService}
 * interface, offering the structural logic to store, fetch and authenticate the
 * user. The authentication is done to ensure access to particular users to the
 * other services exposed by the system
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html">
 *      Service </a>
 * @author Kabir Akware
 */
@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

	/**
	 * {@link UserDetailsRepo} object
	 */
	private UserDetailsRepo userDetailsRepo;

	/**
	 * Method to authenticate the user in the system by verifying the user name and
	 * password shared by the user with the details saved in the database.
	 */
	@Override
	@Cacheable(value = "loadUserByUsername")
	public UserDetails loadUserByUsername(String user) throws UserNameNotFound {
		return UserPricipal.getInstance(userDetailsRepo.findById(user)
				.orElseThrow(() -> UserNameNotFound.getInstance(user + " not found!!")));
	}

	/**
	 * Method to register the user in the system by persisting it in the database
	 * 
	 * <p>
	 * This implementation gets the {@code user name} and {@code password} given by
	 * the user and persists them in the database. The passwords are hashed and
	 * stored to maintain privacy. This method also updates the data for a
	 * particular user if the data is present in the database.
	 * </p>
	 * 
	 */
	@Override
	public UserDto register(UserDto userDto) throws AppException {
		Users user = Users.getInstance(userDto.getUsername(),
				new BCryptPasswordEncoder(AppProperties.intProperty("bcrypt.strength")).encode(userDto.getPassword()),
				(Objects.isNull(userDto.getAuthorities()) || userDto.getAuthorities().isBlank()) ? "USER"
						: userDto.getAuthorities().toUpperCase());
		try {
			userDetailsRepo.save(user);
		} catch (Exception e) {
			throw AppException.getInstance("Something went wrong!! Kindly contact administrator", e.getMessage(),
					Codes.ERR01, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return UserDto.getInstance(userDto.getRefNo(), CommonUtility.getCurrentTimeStamp(), Codes.S00,
				user.getUserName(), user.getPassword(), user.getAuthorities());
	}

	/**
	 * Method to register the user in the system by persisting it in the database
	 * 
	 * <p>
	 * This implementation gets the {@code user name} and {@code password} given by
	 * the user and persists them in the database. The passwords are hashed and
	 * stored to maintain privacy. This method also updates the data for a
	 * particular user if the data is present in the database.
	 * </p>
	 * @throws AppException 
	 * 
	 */
	@Override
	public List<Users> getUsers() throws AppException {
		try {
			return userDetailsRepo.findAll();
		} catch (Exception e) {
			throw AppException.getInstance("Something went wrong!! Kindly contact administrator", e.getMessage(),
					Codes.ERR01, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
