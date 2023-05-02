package jp.co.axa.apidemo.controllers;

/**
 * This class is a Spring RestController that handles HTTP requests for Department entities.
 * It exposes endpoints for retrieving, creating, updating and deleting.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.services.DepartmentService;
import jp.co.axa.apidemo.entities.Department;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
 
    private final DepartmentService departmentService;
 
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
 
    /**
     * Handles GET requests to retrieve a page of Department entities.
     * 
     * @param pageable with default size and sorting
     * @return ResponseEntity containing a page of Department entities and an HTTP status code
     */
    @GetMapping
    public ResponseEntity<Page<Department>> getDepartments(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Department> departments = departmentService.getDepartments(pageable);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
    
    /**
     * Handles GET requests to retrieve a Department entity by ID.
     * 
     * @param id 
     * @return A ResponseEntity containing a Department entity and an HTTP status code
     */
    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) {
    	try {
            Department department = departmentService.getDepartment(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles GET requests to retrieve a Department entity by name.
     * 
     * @param name
     * @return A ResponseEntity containing a Department entity and an HTTP status code
     */
    @GetMapping("/department/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable("name") String name) {
    	try {
	        Department department = departmentService.getDepartmentByName(name);
	        return new ResponseEntity<>(department, HttpStatus.OK);
    	} catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles POST requests to create a new Department entity.
     * 
     * @param department object
     * @return A ResponseEntity containing the created Department entity and an HTTP status code
     */
    @PostMapping("/department")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    /**
     * Handles PUT requests to update an existing Department entity.
     * 
     * @param department object
     * @return A ResponseEntity containing the updated Department entity and an HTTP status code
     */
    @PutMapping("/department")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    /**
     * Handles DELETE requests to delete a Department entity by ID.
     * 
     * @param id 
     * @return A ResponseEntity containing an HTTP status code
     */
    @DeleteMapping("/department/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
