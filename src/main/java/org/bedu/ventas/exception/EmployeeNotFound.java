package org.bedu.ventas.exception;

import org.bedu.ventas.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class EmployeeNotFound extends ErrorDTO {

    public EmployeeNotFound(long employeeId){
        super(HttpStatus.NOT_FOUND, "Employee Not Found ", employeeId);
    }
    
}
