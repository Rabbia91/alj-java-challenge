package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Department;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<Page<Employee>> getEmployees(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Employee> employees = employeeService.getEmployees(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
    	try {
	        Employee employee = employeeService.getEmployee(id);
	        return new ResponseEntity<>(employee, HttpStatus.OK);
    	} catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/departments/{departmentName}")
    public ResponseEntity<Page<Employee>> getEmployeesByDepartment(@PathVariable("departmentName") String departmentName,
    		@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Employee> employees = employeeService.getEmployeesByDepartment(departmentName, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/names/{name}")
    public ResponseEntity<Page<Employee>> getEmployeesByName(@PathVariable("name") String name,
    		@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Employee> employees = employeeService.getEmployeesByName(name, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    	Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
