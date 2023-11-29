package org.bedu.ventas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long employeeID;
    private String LastName;
    private String FirstName;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String BirthDate;

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private String HireDate;
}
