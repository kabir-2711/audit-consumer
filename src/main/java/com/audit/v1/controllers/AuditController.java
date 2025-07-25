package com.audit.v1.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audit.exception.AppException;
import com.audit.services.AuditService;
import com.model.dto.AuditDto;
import com.model.projections.AuditProjection;

import lombok.AllArgsConstructor;

/**
 * The {@code AuditController} class handles HTTP requests related to encryption
 * and decryption operations
 * 
 * <p>
 * This class is a Spring Boot REST controller, which maps HTTP requests to
 * handler methods of REST controllers.
 * </p>
 * 
 * It provides end points to encrypt ({@code /enc-service/encrypt}) and decrypt
 * ({@code /enc-service/decrypt}) for {@literal POST Requests} the pay-load with
 * annotation {@code} @RequestBody}
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html">
 *      RestController</a>
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html">
 *      RequestMapping</a>
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html">
 *      RequestBody</a>
 * @author Kabir Akware
 */
@RestController
@RequestMapping("v1")
@AllArgsConstructor
public class AuditController {

	/**
	 * {@code AuditService} interface parameter
	 */
	private AuditService auditService;

	/**
	 * Fetches the audit logs for end point ({@code /audit})
	 * 
	 * @param page  Page no to be fetched
	 * @param limit Limit of rows to fetch from data base
	 * 
	 * @return List of {@code Audit} object in JSON
	 *         ({@code {"timestamp": "yyyy-MM-dd HH:mm:ss.SSSSSS", "code": "code",
	 *         "data": "Audit logs"}})
	 * @throws AppException Thrown when a custom exception occurs
	 */
	@GetMapping({ "audit", "audit/{page}", "audit/{page}/{limit}" })
	public ResponseEntity<List<AuditProjection>> audit(@PathVariable(required = false) Integer page,
			@PathVariable(required = false) Integer limit) throws AppException {
		return ResponseEntity.ok().body(
				auditService.getAuditLogs(Optional.ofNullable(page).orElse(0), Optional.ofNullable(limit).orElse(5)));
	}

	/**
	 * Fetches the audit logs for end point ({@code /audit})
	 * 
	 * @param page  Page no to be fetched
	 * @param limit Limit of rows to fetch from data base
	 * 
	 * @return List of {@code Audit} object in JSON
	 *         ({@code {"timestamp": "yyyy-MM-dd HH:mm:ss.SSSSSS", "code": "code",
	 *         "data": "Audit logs"}})
	 * @throws AppException Thrown when a custom exception occurs
	 */
	@PostMapping("ref-no-count")
	public ResponseEntity<Integer> refNoCount(AuditDto audit) throws AppException {
		return ResponseEntity.ok()
				.body(auditService.refNoCount(audit.getRefNo(), LocalDateTime.parse(audit.getTill())));
	}
}
