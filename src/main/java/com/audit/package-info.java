/**
 * This Spring Boot Starter provides auto-configuration for integrating MySQL
 * database connectivity into a Spring Boot application.
 *
 * <p>
 * The starter includes the necessary dependencies for MySQL and auto-configures
 * the DataSource and JdbcTemplate based on the application's properties.
 * 
 * <h2>Usage</h2>
 * <p>
 * To use this starter, simply include it as a dependency in your Spring Boot
 * application:
 * 
 * <pre>
 * &#64;SpringBootApplication
 * public class MyApplication {
 * 	public static void main(String[] args) {
 * 		SpringApplication.run(MyApplication.class, args);
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * The starter will automatically configure a {@code DataSource} bean if the
 * following properties are present in the {@code application.properties}:
 * 
 * <pre>
 * spring.datasource.url=jdbc:{db-connection}://{db-ip}:{db-port}/{db}
 * spring.datasource.username={db-username}
 * spring.datasource.password={db-password}
 * </pre>
 *
 * <h2>Components</h2>
 * <ul>
 * <li>Auto-Configuration classes that set up the necessary beans</li>
 * <li>Connection Pooling using HikariCP</li>
 * <li>JDBC Template Support</li>
 * </ul>
 * 
 * 
 * @author Kabir Akware
 */

package com.audit;