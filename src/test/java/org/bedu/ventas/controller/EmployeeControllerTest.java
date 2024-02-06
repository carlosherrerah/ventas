package org.bedu.ventas.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.bedu.ventas.exception.EmployeeNotFoundException;
import org.bedu.ventas.service.EmployeeService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeControllerTest {

    @MockBean
    private EmployeeService service;

    @Autowired
    private EmployeeController controller;

    // @Autowired
    // private TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        controller.deleteEmployee(100L);
    }

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    // ->
    @Test
    @DisplayName("GET /employee should return an employee")
    void getEmployeeTest() throws EmployeeNotFoundException {
        // given(service.getEmployee(anyLong())).willReturn(Optional.of(org.bedu.ventas.dto.EmployeeDTO.builder().employeeid(100L).lastname("Lopez").firstname("Chabelo").build()));
        // mockMvc.perform(get("/cliente/{clienteId}", 1)
        // .content(MediaType.APPLICATION_JSON_VALUE))

        // ResponseEntity<Employee> response = restTemplate.getForEntity("/employees/1",
        // Employee.class);
        // assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        // assertThat(response.getBody().getEmployeeid(), equalTo(1L));
        EmployeeDTO fakeEmployee = new EmployeeDTO();

        fakeEmployee.setEmployeeid(100);
        fakeEmployee.setLastname("Pepito");

        Mockito.when(service.getEmployee(100L)).thenReturn(fakeEmployee);
        EmployeeDTO result = controller.getEmployee(100L);

        assertNotNull(result);
        assertEquals(fakeEmployee.getEmployeeid(), result.getEmployeeid());
        assertEquals(fakeEmployee.getLastname(), result.getLastname());

        Mockito.verify(service, Mockito.times(1)).getEmployee(100L);

    }

    @Test
    @DisplayName("GET /employee should not return an employee")
    void getEmployeeNotFoundTest() throws EmployeeNotFoundException {
        Long employeeid = 100L;

        Mockito.when(service.getEmployee(employeeid))
                .thenThrow(new EmployeeNotFoundException(employeeid));

        assertThrows(EmployeeNotFoundException.class, () -> controller.getEmployee(employeeid));

        Mockito.verify(service, Mockito.times(1)).getEmployee(employeeid);

    }

    // <-
    @Test
    @DisplayName("GET /movies should return a list of movies")
    void findAllTest() {
        // Arrange
        List<EmployeeDTO> fakeData = new LinkedList<>();

        EmployeeDTO fakeEmployee = new EmployeeDTO();

        fakeEmployee.setEmployeeid(100);
        fakeEmployee.setLastname("Pepito");
        fakeData.add(fakeEmployee);

        // Simulando el resultado de ejecutar el método findAll()
        when(service.findAll()).thenReturn(fakeData);

        // Act
        List<EmployeeDTO> result = controller.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(fakeData, result);
        assertEquals(fakeEmployee.getEmployeeid(), result.get(0).getEmployeeid());
        assertEquals(fakeEmployee.getLastname(), result.get(0).getLastname());
    }

    @Test
    @DisplayName("GET /movies should return a empty list of employees")
    void emptyFindAllTest() {
        List<EmployeeDTO> fakeData = new LinkedList<>();
        // Simulando el resultado de ejecutar el método findAll()
        when(service.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<EmployeeDTO> result = controller.findAll();

        // Assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("Controller should save a employee")
    void saveTest() {
        CreateEmployeeDTO dto = new CreateEmployeeDTO();

        dto.setLastname("Sinatra2");
        dto.setFirstname("Frank");

        EmployeeDTO employee = new EmployeeDTO();

        employee.setEmployeeid(100);
        employee.setLastname(dto.getLastname());
        employee.setFirstname(dto.getFirstname());
        when(service.save(any(CreateEmployeeDTO.class))).thenReturn(employee);

        EmployeeDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(employee.getEmployeeid(), result.getEmployeeid());
        assertEquals(employee.getLastname(), result.getLastname());
        assertEquals(employee.getFirstname(), result.getFirstname());
        assertEquals(employee, result);
    }

    @Test
    @DisplayName("Controller should update a movie")
    void updateTest() throws EmployeeNotFoundException {
        UpdateEmployeeDTO dto = new UpdateEmployeeDTO();

        dto.setLastname("Sinatra3");
        dto.setFirstname("Frank");

        controller.update(100, dto);

        // Verificando que el método update del servicio
        // haya sido ejecutado 1 vez
        verify(service, times(1)).update(100, dto);
    }

    @Test
    @DisplayName("Controller should delete a movie")
    void deleteByIdTest() {
        controller.deleteEmployee(101L);

        verify(service, times(1)).delete(101);

    }

    @Test
    @DisplayName("GET /employee/Order should return an employee with Orders")
    void findAllEmployeeOrderTest() throws EmployeeNotFoundException {

        EmployeeWithOrdersDTO fakeEmployeeWithOrdersDTO = new EmployeeWithOrdersDTO();

        fakeEmployeeWithOrdersDTO.setEmployeeid(100L);
        fakeEmployeeWithOrdersDTO.setLastname("Pepito");
        fakeEmployeeWithOrdersDTO.setOrders(Collections.emptyList());
        ;

        Mockito.when(service.findByIdWithOrders(anyLong())).thenReturn(fakeEmployeeWithOrdersDTO);

        EmployeeWithOrdersDTO result = controller.findAllEmployeeOrders(100L);

        assertNotNull(result);
        assertEquals(fakeEmployeeWithOrdersDTO.getEmployeeid(), result.getEmployeeid());
        assertEquals(fakeEmployeeWithOrdersDTO.getLastname(), result.getLastname());
        assertEquals(Collections.emptyList(), result.getOrders());

    }

    @Test
    @DisplayName("GET /employee/Order should not return an employee with Orders")
    void findAllEmployeeOrderNotFoundTest() throws EmployeeNotFoundException {
        Long employeeid = 100L;
        Mockito.when(service.findByIdWithOrders(anyLong())).thenThrow(new EmployeeNotFoundException(employeeid));
        Mockito.verify(service, Mockito.times(0)).getEmployee(employeeid);
    }

}
