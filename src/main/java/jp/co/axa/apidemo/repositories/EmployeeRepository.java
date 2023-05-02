package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	/**
     * Finds all employees by department name.
     * @param departmentName 
     * @return a list of employees in the given department.
     */
    List<Employee> findByDepartment(String departmentName);

    /**
     * Finds all employees by name.
     * @param name 
     * @return a list of employees with the given name.
     */
    List<Employee> findByName(String name);

    /**
     * Finds all employees with pagination.
     * @param pageable
     * @return a page of employees.
     */
    Page<Employee> findAll(Pageable pageable);

    /**
     * Finds all employees in the given department with pagination.
     * @param departmentName 
     * @param pageable
     * @return a page of employees in the given department.
     */
    Page<Employee> findByDepartment(String departmentName, Pageable pageable);

    /**
     * Finds all employees with the given name with pagination.
     * @param name 
     * @param pageable 
     * @return a page of employees with the given name.
     */
    Page<Employee> findByName(String name, Pageable pageable);
}
