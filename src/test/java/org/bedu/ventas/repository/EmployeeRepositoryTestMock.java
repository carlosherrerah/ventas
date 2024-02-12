package org.bedu.ventas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bedu.ventas.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTestMock {

   private EmployeeRepository repository;

    @BeforeEach
    void setUp() {
        // Configuración de EmployeeRepository con Mockito
        repository = mock(EmployeeRepository.class);
    }

    @Test
    @DisplayName("Repository should save an employee")  // $$E
    void saveTest() {
       // Arrange
       Employee employeeToSave = new Employee();
       employeeToSave.setFirstname("John");
       employeeToSave.setLastname("Doe");

       // Mock del método save del repository
       Employee savedEmployee = new Employee();
       savedEmployee.setEmployeeid(100L);
       when(repository.save(any(Employee.class))).thenReturn(savedEmployee);

       // Act
       Employee result = repository.save(employeeToSave);

       // Assert
       assertNotNull(result);
       assertEquals(100L, result.getEmployeeid()); // Asumiendo que el ID se establece correctamente en el repositorio
       verify(repository, times(1)).save(any(Employee.class));

    }

    @Test
    @DisplayName("Repository should find an employee by ID")
    void findEmployeeByIdTest() {
        // Arrange
        Employee existingEmployee = new Employee();
        existingEmployee.setEmployeeid(100L);
        existingEmployee.setFirstname("Jane");
        existingEmployee.setLastname("Doe");

        // Mock del método findById del repository
        when(repository.findById(1L)).thenReturn(Optional.of(existingEmployee));

        // Act
        Optional<Employee> result = repository.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(existingEmployee.getFirstname(), result.get().getFirstname());
        assertEquals(existingEmployee.getLastname(), result.get().getLastname());
    }
    
}
