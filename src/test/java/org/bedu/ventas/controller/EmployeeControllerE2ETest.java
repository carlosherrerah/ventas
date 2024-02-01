package org.bedu.ventas.controller;

import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup(){}

    @Test
    @DisplayName("GET /movies should return a list of movies")
    void findAllTest() throws Exception {
        Employee employee1 = new Employee();

        employee1.setLastname("YoVoy");
        employee1.setFirstname("Bus");

        repository.save(employee1);

        MvcResult result = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<EmployeeDTO>> listTypeReference = new TypeReference<List<EmployeeDTO>>() {
        };

        // Convertimos el JSON a un objeto de Java
        List<EmployeeDTO> response = mapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        //assertEquals(employee1.getLastname(),  response.get(0).getLastname());
        assertEquals(employee1.getFirstname(), response.get(response.size()-1).getFirstname());
        assertEquals(employee1.getLastname(), response.get(response.size()-1).getLastname());
    }

}
