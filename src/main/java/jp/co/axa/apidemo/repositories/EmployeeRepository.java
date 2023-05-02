package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByDepartment(String departmentName);
    List<Employee> findByName(String name);
    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findByDepartment(String departmentName, Pageable pageable);
    Page<Employee> findByName(String name, Pageable pageable);
}
