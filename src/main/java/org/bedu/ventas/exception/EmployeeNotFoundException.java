package org.bedu.ventas.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Long employeeid){
        super("ERROR_Dato_NO_Encontrado", "No se encontró el empleado especificado", employeeid);
    }
}
