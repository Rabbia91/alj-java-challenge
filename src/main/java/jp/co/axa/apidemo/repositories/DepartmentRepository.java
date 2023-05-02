package jp.co.axa.apidemo.repositories;

/**
 * This repository interface provides methods for accessing and manipulating Department entities in the database.
 */

import jp.co.axa.apidemo.entities.Department;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
	/**
     * Finds a Department entity by name.
     * 
     * @param name
     * @return an Optional containing the Department entity if found, or an empty Optional otherwise
     */
    Optional<Department> findByName(String name);
    
    /**
     * Finds all Department entities.
     * 
     * @param pageable 
     * @return a Page containing the Department entities
     */
    Page<Department> findAll(Pageable pageable);
}
