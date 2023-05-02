package jp.co.axa.apidemo.services;

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

	@Override
	@Cacheable("departments")
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}
	
	@Override
	@Cacheable("departments")
	public Page<Department> getDepartments(Pageable pageable) {
		return departmentRepository.findAll(pageable);
	}

	@Override
	@Cacheable(value = "departments", key = "#employeeId")
	public Department getDepartment(Long departmentId) {
		Optional<Department> optDept = departmentRepository.findById(departmentId);
		return optDept.orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
	}
	
	@Override
	@Cacheable(value = "departments", key = "#name")
	public Department getDepartmentByName(String name) {
		Optional<Department> optDept = departmentRepository.findByName(name);
		return optDept.orElseThrow(() -> new RuntimeException("Department not found with name: " + name));
	}

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@CacheEvict(value = "departments", allEntries = true)
	@Override
	public Department updateDepartment(Department department) {
		return departmentRepository.save(department);
		
	}

	@CacheEvict(value = "departments", allEntries = true)
	@Override
	public void deleteDepartment(Long departmentId) {
		departmentRepository.deleteById(departmentId);
		
	}

	
}
