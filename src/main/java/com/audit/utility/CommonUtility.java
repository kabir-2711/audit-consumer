package com.audit.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.utilities.property.AppProperties;

/**
 * This class provides the logic for common utility methods used through out the
 * application
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html">
 *      Component </a>
 * @author Kabir Akware
 */
@Component
public class CommonUtility {

	/**
	 * Method to get the current time stamp string in a pre-defined format
	 * 
	 * @return Current time stamp
	 */
	public static String getCurrentTimeStamp() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(AppProperties.strProperty("time.stamp.format")));
	}
}
