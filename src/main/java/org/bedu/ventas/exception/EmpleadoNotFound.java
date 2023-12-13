package org.bedu.ventas.exception;

import org.springframework.http.HttpStatus;

public class EmpleadoNotFound  extends RuntimeException {
     public EmpleadoNotFound(long employeeId){
        super("Empleado No Encontrado");
    }
}
