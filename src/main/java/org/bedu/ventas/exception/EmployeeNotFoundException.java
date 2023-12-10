package org.bedu.ventas.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Long employeeid){
        super("ERR_DATA_NOT_FOUND", "No se encontró el empleado especificado", employeeid);
    }
}
