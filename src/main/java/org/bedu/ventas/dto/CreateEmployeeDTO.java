package org.bedu.ventas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEmployeeDTO {

    @NotBlank(message = "El apellido no puede quedar vacío")
    private String lastname;

    @NotBlank(message = "El nombre no puede quedar vacío")
    private String firstname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String hiredate;
}
