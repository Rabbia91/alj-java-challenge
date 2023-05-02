package jp.co.axa.apidemo.services;

/**

* The DepartmentServiceImpl class implements the DepartmentService interface.
* It is responsible for handling the business logic related to the Department entity
* and interacts with the DepartmentRepository for data access.
*/

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Department;
import jp.co.axa.apidemo.repositories.DepartmentRepository;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Get all departments
     *
     * @return a list of all departments
     */
    @Override
    @Cacheable("departments")
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
    
    /**
     * Get departments with pagination
     *
     * @param pageable 
     * @return a page of departments
     */
    @Override
    @Cacheable("departments")
    public Page<Department> getDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    /**
     * Get department by ID
     *
     * @param departmentId
     * @return the department with the given ID
     * @throws RuntimeException if the department is not found
     */
    @Override
    @Cacheable(value = "departments", key = "#employeeId")
    public Department getDepartment(Long departmentId) {
        Optional<Department> optDept = departmentRepository.findById(departmentId);
        return optDept.orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
    }
    
    /**
     * Get department by name
     *
     * @param name 
     * @return the department with the given name
     * @throws RuntimeException if the department is not found
     */
    @Override
    @Cacheable(value = "departments", key = "#name")
    public Department getDepartmentByName(String name) {
        Optional<Department> optDept = departmentRepository.findByName(name);
        return optDept.orElseThrow(() -> new RuntimeException("Department not found with name: " + name));
    }

    /**
     * Save a new department
     *
     * @param department object
     * @return the saved department
     */
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Update an existing department
     *
     * @param department object
     * @return the updated department
     */
    @CacheEvict(value = "departments", allEntries = true)
    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);    
    }

    /**
     * Delete a department by ID
     *
     * @param departmentId
     */
    @CacheEvict(value = "departments", allEntries = true)
    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);    
    }
}