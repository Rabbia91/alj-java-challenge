package jp.co.axa.apidemo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jp.co.axa.apidemo.entities.Department;
import jp.co.axa.apidemo.services.DepartmentService;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
	
	@Mock
	private DepartmentService departmentService;
	
	private DepartmentController departmentController;
	
	@BeforeEach
	public void setUp() {
		departmentService = mock(DepartmentService.class);
		departmentController = new DepartmentController(departmentService);
	}
	
	@Test
	public void testGetDepartments() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Department> expectedDepartments = new PageImpl<Department>(Arrays.asList(
				new Department(1L, "Department of Magical Law Enforcement"),
				new Department(2L, "Department for the Regulation and Control of Magical Creatures"),
				new Department(3L, "Department of Magical Games and Sports")
		));
		when(departmentService.getDepartments(pageable)).thenReturn(expectedDepartments);
		
		ResponseEntity<Page<Department>> response = departmentController.getDepartments(pageable);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedDepartments, response.getBody());
	}
	
	@Test
	public void testGetDepartmentById() {

        Long id = 1L;
        Department expectedDepartment = new Department(id, "Department of Mysteries");

        when(departmentService.getDepartment(id)).thenReturn(expectedDepartment);

        ResponseEntity<Department> response = departmentController.getDepartmentById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDepartment, response.getBody());

	}
	
	@Test
	public void testGetDepartmentByIdNotFound() {

		Long id = 5L;
	    when(departmentService.getDepartment(id)).thenThrow(new RuntimeException("Department not found"));
		
		ResponseEntity<Department> response = departmentController.getDepartmentById(id);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testGetDepartmentByName() {
		// arrange
		String name = "Department of International Magical Cooperation";
		Department expectedDepartment = new Department(1L, name);
		when(departmentService.getDepartmentByName(name)).thenReturn(expectedDepartment);
		
		// act
		ResponseEntity<Department> response = departmentController.getDepartmentByName(name);
		
		// assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedDepartment, response.getBody());
	}
	
	@Test
	public void testGetDepartmentByNameNotFound() {

		String name = "The Atrium";
		when(departmentService.getDepartmentByName(name)).thenThrow(new RuntimeException("Department not found"));
		
		ResponseEntity<Department> response = departmentController.getDepartmentByName(name);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
    public void testSaveDepartment() {

		Department department = new Department();
        department.setName("Department of Mysteries");
        when(departmentService.saveDepartment(department)).thenReturn(department);
 
        ResponseEntity<Department> response = departmentController.saveDepartment(department);
 
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(department, response.getBody());
    }
 
    @Test
    public void testUpdateDepartment() {

    	Department department = new Department(1L, "Department of Mysteries");
        when(departmentService.updateDepartment(department)).thenReturn(department);
 
        ResponseEntity<Department> response = departmentController.updateDepartment(department);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(department, response.getBody());
    }
 
    @Test
    public void testDeleteDepartment() {

    	Long id = 1L;
 
        ResponseEntity<Void> response = departmentController.deleteDepartment(id);
 
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentService, times(1)).deleteDepartment(id);
    }
}