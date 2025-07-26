package com.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring-boot Starter Class
 * 
 * <p>
 * Official Spring documentation:
 * <a href = "https://docs.spring.io/spring-boot/documentation.html"> docs </a>
 * </p>
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/annotation/EnableCaching.html">
 *      EnableCaching</a>
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/EnableScheduling.html">
 *      EnableScheduling</a>
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/ComponentScan.html">
 *      ComponentScan</a>
 * @see <a href =
 *      "https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html">
 *      SpringBootApplication</a>
 * @author Kabir Akware
 */
@EnableCaching
@EnableScheduling
@EntityScan(basePackages = "com.model.entity")
@SpringBootApplication
public class Application {

	/**
	 * Java main method to invoke Spring-boot Application
	 * 
	 * @param args Arguments passed while running the jar file in an Array of String
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
