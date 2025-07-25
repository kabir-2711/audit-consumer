package com.audit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.entity.Users;

import jakarta.transaction.Transactional;

/**
 * Repository interface for managing {@link Users} entities.
 * <p>
 * This interface provides basic {@code CRUD operations} by extending the
 * {@link JpaRepository} and allows for custom query methods if needed. The
 * implementation of this interface is automatically provided by Spring Data JPA
 * at runtime.
 * </p>
 * 
 * <h2>Usage</h2>
 * <p>
 * The {@code UserDetailsRepo} can be injected into service or controller layers
 * to perform operations on the {@link Users} entity, such as saving new user
 * details.
 * </p>
 * 
 * <pre>
 *  {@code
 * @Autowired
 * private UserDetailsRepo userDetailsRepo;
 * 
 * public static void someMethod() {
 * 	Users users = new Users();
 * 	// some operations
 * 	userDetailsRepo.save(users);
 * }
 * }
 *  </pre>
 * 
 * <p>
 * Methods used:
 * </p>
 * 
 * <ul>
 * <li>{@link #save(Object)} - saves or updates the user entity in the
 * database</li>
 * <li>{@code #findById(String)} - fetches the user entity for a particular
 * primary key from the database</li>
 * </ul>
 *
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html">
 *      JpaRepository </a>
 * @see Users
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
public interface UserDetailsRepo extends JpaRepository<Users, String> {

}
