package org.bedu.ventas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class UpdateEmployeeDTO {
    @Schema(example = "Macias", description = "Actualizar apellido de empleado")
    @NotBlank
    private String lastname;
    @Schema(example = "Adrian", description = "Actualizar nombre de empleado")
    @NotBlank
    private String firstname;
    @Schema(example = "1990-01-01", description = "Actualizar fecha de nacimiento", format = "date")
    private Date birthdate;
    @Schema(example = "2021-01-01", description = "Actualizar fecha de contratación", format = "date")
    private Date hiredate;
}
