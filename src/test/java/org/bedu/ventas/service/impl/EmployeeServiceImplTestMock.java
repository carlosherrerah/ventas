package org.bedu.ventas.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.bedu.ventas.exception.EmployeeNotFoundException;
import org.bedu.ventas.mapper.EmployeeMapper;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.repository.EmployeeRepository;
import org.bedu.ventas.service.EmployeeService;

import java.util.List;
import java.time.LocalDate;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)    // $$ Aun no funciona
@SpringBootTest
class EmployeeServiceImplTestMock {

    @MockBean
    private EmployeeRepository repository;

    @MockBean
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }
/*   
    @Test
    @DisplayName("Service should save an employee in repository")
    void saveTest() {
         LocalDate birthdate1 = LocalDate.of(1996, 6, 15);
         Date birthdate2 = new Date(96, 5, 15);

         Employee employee = new Employee();
         employee.setEmployeeid(100L);
         employee.setLastname("Sinatra");
         employee.setFirstname("Frank");

         EmployeeDTO employeeDto = new EmployeeDTO();
         employeeDto.setEmployeeid(employee.getEmployeeid());
         employeeDto.setLastname(employee.getLastname());
         employeeDto.setFirstname(employee.getFirstname());

         CreateEmployeeDTO dto = new CreateEmployeeDTO();
         dto.setLastname(employee.getLastname());
         dto.setFirstname(employee.getFirstname());
         dto.setBirthdate(birthdate2);
         dto.setHiredate(birthdate2);

         // Configura el comportamiento del mock repository
         // .when(repository.save(any(Employee.class))).thenReturn(model);
         //when(repository.save(any(Employee.class))).thenReturn(new Employee());
         
         // Configura el comportamiento del mock mapper
         // when(employeeMapper.toModel(dto)).thenReturn(employee);
         // when(employeeMapper.toModel(any(CreateEmployeeDTO.class))).thenReturn(new Employee());
         
         //when(repository.findById(anyLong())).thenReturn(Optional.of(employee));
         when(employeeMapper.toDTO(any(Employee.class))).thenReturn(employeeDto);
         when(repository.save(any(Employee.class))).thenReturn(employee);

         // Llama al método save en service
         EmployeeDTO result = service.save(dto);   // error
         
         assertNotNull(result);
         //assertNotNull(result.getEmployeeid());
         //assertEquals(model.getLastname(), result.getLastname());
         //assertEquals(model.getFirstname(), result.getFirstname());
         
         verify(repository, times(1)).save(any(Employee.class));
    }
*/
 
}


