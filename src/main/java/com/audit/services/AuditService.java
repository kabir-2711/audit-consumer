package com.audit.services;

import java.time.LocalDateTime;
import java.util.List;

import com.model.projections.AuditProjection;

/**
 * This Interface defines the contract for {@code AuditService} service. The
 * service provides functionalities for storing and managing requests and
 * responses in database for audit purpose.
 * 
 * <p>
 * The implementation of this service can be used to track database activities
 * for security, debugging, or data integrity purposes. It can be invoked from
 * service or repository layers.
 * </p>
 * 
 * 
 * @author Kabir Akware
 */
public interface AuditService {

	/**
	 * Declaration of {@code getAuditLogs} to get all the audit logs
	 * 
	 * @param page  Page no to be fetched
	 * @param limit limit of data to be fetched
	 * 
	 * @return List of log entries
	 */
	List<AuditProjection> getAuditLogs(Integer page, Integer limit);

	/**
	 * Declaration of {@code refNoCount} to get the count of reference number
	 * present in the data base
	 * 
	 * @param refNo    Reference number
	 * @param pastTime Time till which the reference number should be checked
	 * @return Count of reference number present in the data base
	 */
	int refNoCount(String refNo, LocalDateTime pastTime);
}
