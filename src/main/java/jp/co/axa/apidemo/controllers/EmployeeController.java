package jp.co.axa.apidemo.controllers;

/*
* * This class is a Spring RestController that handles HTTP requests for Department entities.
* It exposes endpoints for retrieving, creating, updating and deleting.
*/


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

    /**
     * Handles GET requests to retrieve a page of Emplooyees entities.
     * 
     * @param pageable with default size and sorting
     * @return ResponseEntity containing a page of Department entities and an HTTP status code
     */
    @GetMapping("/employees")
    public ResponseEntity<Page<Employee>> getEmployees(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Employee> employees = employeeService.getEmployees(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    /**
     * Handles GET requests to retrieve a Employee entity by ID.
     * 
     * @param id 
     * @return A ResponseEntity containing a Employee entity and an HTTP status code
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
    	try {
	        Employee employee = employeeService.getEmployee(id);
	        return new ResponseEntity<>(employee, HttpStatus.OK);
    	} catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles GET requests to retrieve a Employee entity by department name.
     * 
     * @param name
     * @return A ResponseEntity containing a Employee entity and an HTTP status code
     */
    @GetMapping("/employees/departments/{departmentName}")
    public ResponseEntity<Page<Employee>> getEmployeesByDepartment(@PathVariable("departmentName") String departmentName,
    		@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Employee> employees = employeeService.getEmployeesByDepartment(departmentName, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /**
     * Handles GET requests to retrieve a Employee entity by employee name.
     * 
     * @param name
     * @return A ResponseEntity containing a Employee entity and an HTTP status code
     */
    @GetMapping("/employees/names/{name}")
    public ResponseEntity<Page<Employee>> getEmployeesByName(@PathVariable("name") String name,
    		@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Employee> employees = employeeService.getEmployeesByName(name, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /**
     * Handles POST requests to create a new Employee entity.
     * 
     * @param Employee object
     * @return A ResponseEntity containing the created Employee entity and an HTTP status code
     */
    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    /**
     * Handles PUT requests to update an existing Department entity.
     * 
     * @param Employee object
     * @return A ResponseEntity containing the updated Department entity and an HTTP status code
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    	Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    /**
     * Handles DELETE requests to delete a Employee entity by ID.
     * 
     * @param id 
     * @return A ResponseEntity containing an HTTP status code
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
