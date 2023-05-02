package jp.co.axa.apidemo.services;

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

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Cacheable("employees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    
    @Override
    @Cacheable("employees")
	public Page<Employee> getEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

    @Override
    @Cacheable(value = "employees", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
    }
    
    @Override
    @Cacheable(value = "employees", key = "#departmentName")
    public List<Employee> getEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByDepartment(departmentName);
    }
    
    @Override
    @Cacheable(value = "employees", key = "#departmentName")
    public Page<Employee> getEmployeesByDepartment(String departmentName, Pageable pageable) {
        return employeeRepository.findByDepartment(departmentName, pageable);
    }
    
    @Override
    @Cacheable(value = "employees", key = "#name")
	public List<Employee> getEmployeesByName(String name) {
		return employeeRepository.findByName(name);
	}
    
    @Override
	public Page<Employee> getEmployeesByName(String name, Pageable pageable) {
    	return employeeRepository.findByName(name, pageable);
	}

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employees", allEntries = true)
    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @CacheEvict(value = "employees", allEntries = true)
    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }	
    
}
