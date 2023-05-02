package jp.co.axa.apidemo.services;

/**
 * This service interface provides methods for accessing and manipulating Department repository.
 */

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jp.co.axa.apidemo.entities.Department;

public interface DepartmentService {
	
	List<Department> getDepartments();
	
    Page<Department> getDepartments(Pageable pageable);
    
	Department getDepartment(Long departmentId);
    
	Department getDepartmentByName(String name);
    
	Department saveDepartment(Department department);
    
	void deleteDepartment(Long departmentId);
    
	Department updateDepartment(Department department);
}
