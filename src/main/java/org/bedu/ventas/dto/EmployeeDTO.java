package org.bedu.ventas.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmployeeDTO {

    @Schema(description = "Identificador del empleado", example = "20")
    private long employeeid;

    @Schema(description = "Apellido Paterno del Empleado", example = "Gonzalez")
    private String lastname;

    @Schema(description = "Nombre del Empleado", example = "Antonio")
    private String firstname;
    
    @Schema(description = "Fecha de Nacimiento", example = "1970-08-25", format = "date-time", pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @Schema(description = "Fecha de Contratación", example = "2000-10-20", format = "date-time", pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;
    
}
