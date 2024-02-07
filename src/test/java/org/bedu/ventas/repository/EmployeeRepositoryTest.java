package org.bedu.ventas.repository;

import org.bedu.ventas.model.Employee;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private TestEntityManager manager;

    @BeforeEach
    public void setup() {
        // repository.deleteAll();
    }

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

        employee1.setLastname("Lopez");
        employee1.setFirstname("Jennifer");

        employee2.setLastname("Sinatra");
        employee2.setFirstname("Frank");

        manager.persist(employee1);
        manager.persist(employee2);

        List<Employee> result = repository.findByLastnameContaining("Sinatra");

        assertEquals(1, result.size());
        assertEquals(employee2, result.get(0));
    }

    @Test
    @DisplayName("Repository should save an interviewer")
    void saveTest() {
        Employee employee = new Employee();

        employee.setLastname("Musk");
        employee.setFirstname("Elon");

        Employee result = repository.save(employee);

        assertNotNull(result);
    }

}
