package jp.co.axa.apidemo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jp.co.axa.apidemo.entities.Department;
import jp.co.axa.apidemo.repositories.DepartmentRepository;
import jp.co.axa.apidemo.services.DepartmentService;
import jp.co.axa.apidemo.services.DepartmentServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	private DepartmentService departmentService;
	
	@BeforeEach
	void setUp() {
		departmentService = new DepartmentServiceImpl(departmentRepository);
	}
	
	@Test
	public void testGetDepartments() {
		List<Department> expectedDepartments = new ArrayList<>();
		expectedDepartments.add(new Department(1L, "Department of Magical Law Enforcement"));
		expectedDepartments.add(new Department(2L, "Department for the Regulation and Control of Magical Creatures"));
		when(departmentRepository.findAll()).thenReturn(expectedDepartments);
		
		List<Department> actualDepartments = departmentService.getDepartments();
		
		assertThat(actualDepartments).isEqualTo(expectedDepartments);
		verify(departmentRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetDepartmentsWithPagination() {
		List<Department> expectedDepartments = new ArrayList<>();
		expectedDepartments.add(new Department(1L, "Department of Magical Transportation"));
		expectedDepartments.add(new Department(2L, "Department of Magical Games and Sports"));
		Page<Department> expectedPage = new PageImpl<>(expectedDepartments);
		Pageable pageable = PageRequest.of(0, 10);
		when(departmentRepository.findAll(pageable)).thenReturn(expectedPage);
		
		Page<Department> actualPage = departmentService.getDepartments(pageable);
		
		assertThat(actualPage).isEqualTo(expectedPage);
		verify(departmentRepository, times(1)).findAll(pageable);
	}
	
	@Test
	public void testGetDepartment() {
		Department expectedDepartment = new Department(1L, "Department of Mysteries");
		when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(expectedDepartment));
		
		Department actualDepartment = departmentService.getDepartment(1L);
		
		assertThat(actualDepartment).isEqualTo(expectedDepartment);
		verify(departmentRepository, times(1)).findById(anyLong());
	}
	
	@Test
	public void testGetDepartmentByName() {
		Department expectedDepartment = new Department(1L, "Department of Mysteries");
		when(departmentRepository.findByName(any())).thenReturn(Optional.of(expectedDepartment));
		
		Department actualDepartment = departmentService.getDepartmentByName("Department of Mysteries");
		
		assertThat(actualDepartment).isEqualTo(expectedDepartment);
		verify(departmentRepository, times(1)).findByName(any());
	}
	
	@Test
	public void testSaveDepartment() {
		Department expectedDepartment = new Department(1L, "Department of Mysteries");
		when(departmentRepository.save(any())).thenReturn(expectedDepartment);
		
		Department actualDepartment = departmentService.saveDepartment(expectedDepartment);
		
		assertThat(actualDepartment).isEqualTo(expectedDepartment);
		verify(departmentRepository, times(1)).save(any());
	}
	
	@Test
    void testUpdateDepartment() {

		Department department = new Department();
        department.setId(1L);
        department.setName("Department 1");

        when(departmentRepository.save(department)).thenReturn(department);
        Department result = departmentService.updateDepartment(department);

        verify(departmentRepository, times(1)).save(department);
        assertEquals(department.getId(), result.getId());
        assertEquals(department.getName(), result.getName());
    }

    @Test
    void testDeleteDepartment() {

    	Long id = 1L;

        departmentService.deleteDepartment(id);

        verify(departmentRepository, times(1)).deleteById(id);
    }
	
}