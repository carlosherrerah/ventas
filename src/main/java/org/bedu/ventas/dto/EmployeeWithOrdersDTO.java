package org.bedu.ventas.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmployeeWithOrdersDTO {
    @Schema(example = "30", description = "Identificador de empleado")
    private Long employeeid;
    @Schema(example = "López", description = "Apellido del empleado")
    private String lastname;
    @Schema(example = "Juan", description = "Nombre del empleado")
    private String firstname;
    @Schema(example = "2000-01-01" , description = "Fecha de nacimiento del empleado")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdate;
    @Schema(example = "2023-01-01" , description = "Fecha de contratación del empleado", format = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String hiredate;
    @Schema(description = "Lista de pedidos del empleado", format = "date")
    private List<OrderDTO> orders;
}
