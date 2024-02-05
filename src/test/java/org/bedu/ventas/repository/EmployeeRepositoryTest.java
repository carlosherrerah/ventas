package org.bedu.ventas.repository;

import org.bedu.ventas.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    @DisplayName("Repository should be injected")
    void smokeTest() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should filter employees by lastname")
    void testFindByLastnameContaining() {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        employee1.setLastname("Miguel");
        employee1.setFirstname("Luis");

        employee2.setLastname("Fernandez");
        employee2.setFirstname("Alejandro");


        manager.persist(employee1);
        manager.persist(employee2);

        List<Employee> result = repository.findByLastnameContaining("Fernandez");

        assertEquals(1, result.size());
    }

}
