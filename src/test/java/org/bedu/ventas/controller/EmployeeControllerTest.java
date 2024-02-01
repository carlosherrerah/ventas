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
import static org.mockito.Mockito.when;

import org.bedu.ventas.dto.EmployeeDTO;
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

      

}
