package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Department;

/**
 * This service interface provides methods for accessing and manipulating Employee repository.
 */


import jp.co.axa.apidemo.entities.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();
    
    Page<Employee> getEmployees(Pageable pageable);

    Employee getEmployee(Long employeeId);

    List<Employee> getEmployeesByDepartment(Department department);
    
    Page<Employee> getEmployeesByDepartment(Department department, Pageable pageable);
    
    List<Employee> getEmployeesByName(String name);
    
    Page<Employee> getEmployeesByName(String name, Pageable pageable);

    Employee saveEmployee(Employee employee);

    void deleteEmployee(Long employeeId);

    Employee updateEmployee(Employee employee);

}