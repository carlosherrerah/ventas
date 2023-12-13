package org.bedu.ventas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Modelo UpdateEmployeeDTO para actualizar un empleado")
public class UpdateEmployeeDTO {

    @Schema(description = "Apellido del Empleado", example = "Gonzalez")
    @NotBlank
    private String lastname;
    @Schema(description = "Nombre del Empleado", example = "Juan")
    private String firstname;
    @Schema(example = "1990-01-01", description = "Actualizar fecha de nacimiento", format = "date", pattern = "yyyy-MM-dd")
    private Date birthdate;
    @Schema(example = "2021-01-01", description = "Actualizar fecha de contratación", format = "date", pattern = "yyyy-MM-dd")
    private Date hiredate;
}
