package org.bedu.ventas.dto;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEmployeeDTO {
    
    private String lastname;   // En la DB obligatorio

    @Schema(description = "Nombre del Empleado", example = "Gonzalez")
    @NotBlank(message = "El nombre del empleado es obligatorio")
    private String firstname;

    @NotNull(message = "La fecha de nacimiento es obligatoria")    
    private Date birthdate;
    
    private Date hiredate;
    
    //@Email(message = "El correo electrónico debe tener un formato válido")

}
