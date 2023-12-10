package org.bedu.ventas.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateEmployeeDTO {

    @NotBlank
    private String lastname;

    @NotBlank
    private String firstname;

    private Date birthdate;

    private Date hiredate;
}
