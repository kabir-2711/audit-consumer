package com.audit.repo;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.entity.Audit;
import com.model.projections.AuditProjection;

import jakarta.transaction.Transactional;

/**
 * Repository interface for managing {@link Audit} entities.
 * <p>
 * This interface provides basic {@code CRUD operations} by extending the
 * {@link JpaRepository} and allows for custom query methods if needed. The
 * implementation of this interface is automatically provided by Spring Data JPA
 * at runtime.
 * </p>
 * 
 * <h2>Usage</h2>
 * <p>
 * The {@code AuditRepo} can be injected into service or controller layers to
 * perform operations on the {@link Audit} entity, such as saving new audit
 * details.
 * </p>
 * 
 * <pre>
 *  {@code
 * @Autowired
 * private AuditRepo auditRepo;
 * 
 * public static void someMethod() {
 * 	Audit audit = new Audit();
 * 	// some operations
 * 	auditRepo.save(audit);
 * }
 * }
 *  </pre>
 * 
 * <p>
 * Methods used:
 * </p>
 * 
 * <ul>
 * <li>{@link #save(Object)} - saves or updates the audit entity in the
 * database</li>
 * </ul>
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html">
 *      JpaRepository </a>
 * @see Audit
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html">
 *      Repository </a>
 * @see <a href =
 *      "https://jakarta.ee/specifications/transactions/2.0/apidocs/jakarta/transaction/transactional">
 *      Transactional </a>
 * @author Kabir Akware
 */
@Repository
@Transactional
public interface AuditRepo extends JpaRepository<Audit, Integer> {

	/**
	 * Method to get data from {@code service_audit.audit} table with page details
	 * provided in the method parameter
	 * 
	 * @param page {@code Pageable} interface containing pagination details
	 * 
	 * @return List of {@link Audit} entity returned by executing the query
	 */
	Page<AuditProjection> findAllByOrderByIdDesc(Pageable page);

	/**
	 * Method to get count of reference number available in
	 * {@code service_audit.audit} table
	 * 
	 * @param refNo Reference number
	 * @param since Date time till which the query should search
	 * @return Count of reference number for time between {@code now()} and
	 *         {@code since}
	 */
	@Query("select count(a) from Audit a where a.refNo=:refNo and a.date >= :since")
	int refNoCount(@Param("refNo") String refNo, @Param("since") LocalDateTime since);

}
