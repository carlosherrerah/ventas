package org.bedu.ventas.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.bedu.ventas.exception.EmployeeNotFoundException;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.repository.EmployeeRepository;
import org.bedu.ventas.service.EmployeeService;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeServiceImplTest {

    @MockBean
    private EmployeeRepository repository;

    @Autowired
    private EmployeeService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    void testFindAll() {
        List<Employee> data = new LinkedList<>();

        Employee employee = new Employee();

        employee.setEmployeeid(44);
        employee.setLastname("Sinatra");
        employee.setFirstname("Frank");

        data.add(employee);

        when(repository.findAll()).thenReturn(data);

        List<EmployeeDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(employee.getEmployeeid(), result.get(0).getEmployeeid());
        assertEquals(employee.getLastname(), result.get(0).getLastname());

    }

    @Test
    @DisplayName("Service should save a movie in repository")
    void saveTest() {
      CreateEmployeeDTO dto = new CreateEmployeeDTO();
  
      dto.setLastname("Sinatra");
      dto.setFirstname("Frank");
  
      Employee model = new Employee();
  
      model.setEmployeeid(100);
      model.setLastname(dto.getLastname());
      model.setFirstname((dto.getFirstname()));
  
      when(repository.save(any(Employee.class))).thenReturn(model);
  
      EmployeeDTO result = service.save(dto);
  
      assertNotNull(result);
      assertEquals(model.getEmployeeid(), result.getEmployeeid());
      assertEquals(model.getLastname(), result.getLastname());
      assertEquals(model.getFirstname(), result.getFirstname());
    }

    @Test
    @DisplayName("Service should delete an employee by id in repository")
    void testDelete() {
        service.delete(100L);

        verify(repository, times(1)).deleteById(100L);
    }

    @Test
    @DisplayName("Service should update an employee in repository")
    void updateTest() throws EmployeeNotFoundException {
      UpdateEmployeeDTO dto = new UpdateEmployeeDTO();
  
      dto.setLastname("Sinatra");
      dto.setFirstname("Frank");
 
      Employee employee = new Employee();
  
      employee.setEmployeeid(100);
      employee.setLastname("Sinatra");
      employee.setFirstname("Stein");
  
      when(repository.findById(anyLong())).thenReturn(Optional.of(employee));
  
      service.update(100, dto);
  
      assertEquals(dto.getLastname(), employee.getLastname());
      assertEquals(dto.getFirstname(), employee.getFirstname());
      verify(repository, times(1)).save(employee);
    }

    @Test
    @DisplayName("Service should throws an error if movie was not found")
    void updateWithErrorTest() {
      UpdateEmployeeDTO dto = new UpdateEmployeeDTO();
      Optional<Employee> dummy = Optional.empty();
  
      when(repository.findById(anyLong())).thenReturn(dummy);
  
      assertThrows(EmployeeNotFoundException.class, () -> service.update(100, dto));
    }
  

/*
    @Test
    void testFindByIdWithOrders() {

    }

    @Test
    void testGetEmployee() {

    }

    @Test
    void testUpdateParcial() {

    }
*/    
}
