package com.audit.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.audit.exception.AppException;
import com.audit.services.AuthProvider;
import com.google.gson.Gson;
import com.utilities.exceptions.ConfigException;
import com.utilities.log.Log;

import lombok.AllArgsConstructor;

/**
 * The {@code AppConfig} class configures security settings for the Spring Boot
 * application.
 * 
 * <p>
 * This class is the {@code Configuration} class for the application.<br>
 * 
 * This class is responsible for setting up Spring Security, including
 * authentication, authorization, security-related filters and creating
 * {@code Log} object and injecting it in the container as well as initializing
 * other {@code beans} that is required by the application.</br>
 * It also provides custom authentication mechanisms using the
 * {@link AuthenticationProvider}.
 * </p>
 * 
 * <p>
 * Key components of this configuration include:
 * </p>
 * <ul>
 * <li>Configuring authentication using custom
 * {@link AuthenticationProvider}.</li>
 * <li>Defining HTTP security rules for securing end-points.</li>
 * <li>Setting up password encoding using {@link BCryptPasswordEncoder}.</li>
 * <li>Defining custom user details service.</li>
 * <li>Initializing {@link Log} and injecting object in the
 * {@code Application Container} to enable asynchronous logging via KAFKA
 * broker.
 * </ul>
 * 
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * &#64;Configuration
 * &#64;EnableWebSecurity
 * class SecurityConfig {
 * 
 * 	&#64;Bean
 * 	SecurityFilterChain securityFilterChain(HttpSecurity security) {
 * 		// security configurations
 * 	}
 * }
 * </pre>
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/EnableWebSecurity.html">
 *      EnableWebSecurity</a>
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html">
 *      Configuration</a>
 * @author Kabir Akware
 */
@EnableKafka
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class AppConfig {

	/**
	 * {@code AppAuthProvider} interface variable
	 */
	private AuthProvider provider;

	/**
	 * Method to create a {@code @Bean} of {@link SecurityFilterChain} to provide a
	 * customized implementation of the security and add that to the filter chain
	 * before the application layer to perform security checks before providing
	 * access to the resource3s in the system
	 * 
	 * @param security {@link HttpSecurity} object
	 * @return {@link SecurityFilterChain} with all the necessary security checks
	 * @throws AppException Thrown when a custom exception occurs
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws AppException {
		try {
			return security.csrf(AbstractHttpConfigurer::disable)
					.authorizeHttpRequests(request -> request
							.requestMatchers("/v1/users/register", "/v1/channels/register", "/v1/properties/**")
							.hasAuthority("ADMIN").requestMatchers("/v1/audit/**", "/v1/channels/**", "/v1/users/**")
							.permitAll().requestMatchers("/**").hasAuthority("SYSTEM").anyRequest().authenticated())
					.httpBasic(Customizer.withDefaults())
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.authenticationProvider(provider.authenticationProvider()).build();
		} catch (Exception e) {
			throw ConfigException
					.getInstance("Exception occurred while instantiating security filter chain: " + e.getMessage());
		}
	}

	@Bean
	Gson g() {
		return new Gson().newBuilder().disableHtmlEscaping().serializeNulls().serializeSpecialFloatingPointValues()
				.setPrettyPrinting().create();
	}

	@Bean
	KafkaProducer<String, String> kafkaProps(@Value("${config.kafka.server-details}") String bootstrapServers) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.RETRIES_CONFIG, 3);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 5);

		return new KafkaProducer<>(props);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
			@Value("${config.kafka.server-details}") String bootstrapServers) {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory(bootstrapServers));
		factory.setConcurrency(3); // Number of consumer threads
		return factory;
	}

	private ConsumerFactory<String, String> consumerFactory(String bootstrapServers) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return new DefaultKafkaConsumerFactory<>(props);
	}
}
