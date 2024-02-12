package org.bedu.ventas.dto;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(description = "Modelo CreateEmployeeDTO para crear un empleado")
@Data
public class CreateEmployeeDTO {

    private String lastname;   // En la DB obligatorio

    @Schema(description = "Nombre del Empleado", example = "Gonzalez")
    //@NotBlank(message = "El nombre del empleado es obligatorio")
    private String firstname;
    
    @Schema(example = "1990-01-01", description = "Fecha de nacimiento", format = "date-time",pattern = "yyyy-MM-dd")
    //@NotNull(message = "La fecha de nacimiento es obligatoria")
    private Date birthdate;
    
    @Schema(example = "2021-01-01", description = "Fecha de contratación", format = "date-time",pattern = "yyyy-MM-dd")
    private Date hiredate;
    
    //@Email(message = "El correo electrónico debe tener un formato válido")

}
