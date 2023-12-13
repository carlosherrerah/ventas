package org.bedu.ventas.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class UpdateEmployeeDTO {

    @Schema(description = "Apellido del Empleado", example = "Gonzalez")
    @NotBlank
    private String lastname;

    private String firstname;

    private Date birthdate;

    private Date hiredate;
}
