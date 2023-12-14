package org.bedu.ventas.config;

import lombok.extern.slf4j.Slf4j;
import org.bedu.ventas.dto.ErrorDTO;
import org.bedu.ventas.exception.RuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ErrorHandler   {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO validationError(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        // List<String> errors = new LinkedList<>();
        // for (FieldError fieldError : fieldErrors) {
        // errors.add(fieldError.getDefaultMessage());
        // }
        List<String> errors = fieldErrors.stream().map(x -> x.getDefaultMessage()).toList();
        // return new ErrorDTO("ERR_VALID", "Los datos de entrada contiene errores",
        // errors);
        return ErrorDTO.builder()
                .code("ERR_VALID")
                .message("Los datos de entrada contiene errores")
                .details(errors)
                .build();
    }

    
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO runtimeError(RuntimeException ex) {
        log.error("Excepción por recurso no encontrado", ex);        
        // return new ErrorDTO(ex.getCode(), ex.getMessage(), ex.getDetails());
        return ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .details(ex.getDetails())
                .build();
    }

/*
    // Status 404 HttpStatus.BAD_REQUEST Personalizado
    @ResponseStatus
    @ExceptionHandler(ExcepcionRecursoNoEncontrado.class)
    @ResponseBody
    public ResponseEntity<Object> manejaExcepcionEncontrado(ExcepcionRecursoNoEncontrado ex) {
        log.error("Excepción por recurso no encontrado", ex);  
        ErrorDTO eventoError = new ErrorDTO(HttpStatus.NOT_FOUND.toString(), ex.getMessage(), ex.toString());      
        return new ResponseEntity<Object>(eventoError, HttpStatus.NOT_FOUND);
    } // Personalido
*/

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO unknownError(Exception ex) {
        log.error(ex.getMessage());
        return new ErrorDTO("ERROR_UNKNOWN", "Ocurrió un error inesperado...", null);
    }
}
