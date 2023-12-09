package org.bedu.ventas.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(long employeeid){
        super("ERR_DATA_NOT_FOUND", "No se encontró el empleado especificado", employeeid);
    }
}
