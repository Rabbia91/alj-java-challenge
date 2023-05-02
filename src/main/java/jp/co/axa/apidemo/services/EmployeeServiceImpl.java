package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Department;
/**

* The EmployeeServiceImpl class implements the EmployeeService interface.
* It is responsible for handling the business logic related to the Employee entity
* and interacts with the EmployeeRepository for data access.
*/
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    
    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get all employees.
     *
     * @return a list of all employees
     */
    @Override
    @Cacheable("employees")
    public List<Employee> getEmployees() {
    	logger.info("Retrieved all employees");
        return employeeRepository.findAll();
    }
    
    /**
     * Get all employees with pagination.
     *
     * @param pageable 
     * @return a page of employees
     */
    @Override
    @Cacheable("employees")
    public Page<Employee> getEmployees(Pageable pageable) {
    	logger.info("Retrieved all employees with pagination");
        return employeeRepository.findAll(pageable);
    }

    /**
     * Get an employee by their ID.
     *
     * @param employeeId
     * @return the employee with the specified ID
     * @throws RuntimeException if the employee is not found
     */
    @Override
    @Cacheable(value = "employees", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
    	Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        if (optEmp.isPresent()) {
            logger.info("Retrieved employee with id: " + employeeId);
            return optEmp.get();
        } else {
            logger.error("Employee not found with id: " + employeeId);
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
    }
    
    /**
     * Get all employees in a specified department.
     *
     * @param departmentName 
     * @return a list of employees in the specified department
     */
    @Override
    @Cacheable(value = "employees", key = "#departmentName")
    public List<Employee> getEmployeesByDepartment(Department department) {
    	logger.info("Retrieved all employees in department: " + department);
    	return employeeRepository.findByDepartment(department);
    }
    
    /**
     * Get all employees in a specified department with pagination.
     *
     * @param departmentName 
     * @param pageable 
     * @return a page of employees in the specified department
     */
    @Override
    @Cacheable(value = "employees", key = "#departmentName")
    public Page<Employee> getEmployeesByDepartment(Department departmentName, Pageable pageable) {
    	logger.info("Retrieved all employees in department: " + departmentName);
        return employeeRepository.findByDepartment(departmentName, pageable);
    }
    
    /**
     * Get all employees with a specified name.
     *
     * @param name 
     * @return a list of employees with the specified name
     */
    @Override
    @Cacheable(value = "employees", key = "#name")
    public List<Employee> getEmployeesByName(String name) {
    	logger.info("Retrieved all employees by: " + name);
        return employeeRepository.findByName(name);
    }
    
    /**
     * Get all employees with a specified name with pagination.
     *
     * @param name 
     * @param pageable 
     * @return a page of employees with the specified name
     */
    @Override
    public Page<Employee> getEmployeesByName(String name, Pageable pageable) {
    	logger.info("Retrieved all employees by "+ name +" with pagination");
        return employeeRepository.findByName(name, pageable);
    }

    /**
     * Save an employee.
     *
     * @param employee onject
     * @return the saved employee
     */
    @Override
    public Employee saveEmployee(Employee employee) {
    	logger.info("Created new employee: "+ employee.getName()+", "+ employee.getDepartment().getName() + ", " + employee.getSalary() );
        return employeeRepository.save(employee);
    }

    /**
     * Update an existing employee
     *
     * @param employee object
     * @return the updated employee
     */
    @CacheEvict(value = "employees", allEntries = true)
    @Override
    public void deleteEmployee(Long employeeId) {
    	logger.info("Deleted the employees with "+ employeeId );
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Delete a employee by ID
     *
     * @param employeeId
     */
    @CacheEvict(value = "employees", allEntries = true)
    @Override
    public Employee updateEmployee(Employee employee) {
    	logger.info("Updated new employees with : "+ employee.getId() + ", " + employee.getName()+", "+ employee.getDepartment().getName() + ", " + employee.getSalary());
        return employeeRepository.save(employee);
    }	
    
}
