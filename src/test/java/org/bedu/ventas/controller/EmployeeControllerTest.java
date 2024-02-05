package org.bedu.ventas.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
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

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("GET /movies should return a list of movies")
    void findAllTest() {
        // Arrange
        List<EmployeeDTO> fakeData = new LinkedList<>();

        EmployeeDTO fakeEmployee = new EmployeeDTO();

        fakeEmployee.setEmployeeid(41);
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

        dto.setLastname("Herrera");
        dto.setFirstname("Carlos");

        EmployeeDTO employee = new EmployeeDTO();

        employee.setEmployeeid(45);
        employee.setLastname(dto.getLastname());
        employee.setFirstname(dto.getFirstname());
        when(service.save(any(CreateEmployeeDTO.class))).thenReturn(employee);

        EmployeeDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(employee.getEmployeeid(), result.getEmployeeid());
        assertEquals(employee.getLastname(), result.getLastname());
        assertEquals(employee.getFirstname(), result.getFirstname());
    }

    @Test
    @DisplayName("Controller should update a movie")
    void updateTest() throws EmployeeNotFoundException {
        UpdateEmployeeDTO dto = new UpdateEmployeeDTO();

        dto.setLastname("Herrera");
        dto.setFirstname("Carlos");

        controller.update(44, dto);

        // Verificando que el método update del servicio
        // haya sido ejecutado 1 vez
        verify(service, times(1)).update(44, dto);
    }

    @Test
    @DisplayName("Controller should delete a movie")
    void deleteByIdTest() {
        controller.deleteEmployee(44L);

        verify(service, times(1)).delete(44);

    }

}
