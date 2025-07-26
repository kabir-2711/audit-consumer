package com.audit.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.audit.repo.AuditRepo;
import com.audit.services.AuditService;
import com.model.projections.AuditProjection;

import lombok.AllArgsConstructor;

/**
 * This class provides the implementation of {@link AuditService} interface,
 * offering the structural logic for auditing requests and responses to data
 * base for auditing.
 * 
 * <p>
 * This uses {@link AuditRepo} repository to fire the queries and fetch/store
 * the data
 * </p>
 * 
 * <p>
 * This implementation tracks and logs all requests made to the system,
 * including details such as the end points called, the time stamp of the
 * operation, success/error codes, etc.
 * </p>
 * 
 * Features:
 * 
 * <ul>
 * <li>Logs details of each request to the audit table.</li>
 * <li>Can be integrated with various data sources (e.g., relational databases,
 * NoSQL).</li>
 * <li>Provides methods for querying and retrieving audit logs.</li>
 * </ul>
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html">
 *      Service </a>
 * @author Kabir Akware
 */
@Service
@AllArgsConstructor
@DependsOn("appPropertiesInit")
public class AuditServiceImpl implements AuditService {

	/**
	 * {@link AuditRepo} repository object
	 */
	private AuditRepo auditRepo;


	/**
	 * Method implementing the logic to return all the audit logs stored in the data
	 * base using custom method
	 */
	@Override
	public List<AuditProjection> getAuditLogs(Integer page, Integer limit) {
		return auditRepo.findAllByOrderByIdDesc(PageRequest.of(page, limit)).getContent();
	}

	/**
	 * Method implementing the logic to fetch the count of reference number present
	 * in the data base
	 */
	@Override
	public int refNoCount(String refNo, LocalDateTime pastTime) {
		return auditRepo.refNoCount(refNo, pastTime);
	}
}
