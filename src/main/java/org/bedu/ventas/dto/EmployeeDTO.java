package org.bedu.ventas.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long employeeid;
    private String lastname;
    private String firstname;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String hiredate;

    private List<OrderDTO> orders;
}
