package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Department;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
	Optional<Department> findByName(String name);
	Page<Department> findAll(Pageable pageable);
}
