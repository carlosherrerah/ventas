package org.bedu.ventas.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.bedu.ventas.dto.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

class ErrorHandlerTest {

    @InjectMocks
    private ErrorHandler errorHandler;

    @Mock
    private MethodArgumentNotValidException validException;

    @Mock
    private org.bedu.ventas.exception.RuntimeException runtimeException;

    @Mock
    private Exception genericException;

    @Test
    void testValidationError() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        BindingResult bindingResult = mockBindingResult("Field error message");
        when(validException.getBindingResult()).thenReturn(bindingResult);

        // Act
        ErrorDTO errorDTO = errorHandler.validationError(validException);

        // Assert
        assertEquals("ERR_VALID", errorDTO.getCode());
        assertEquals(Collections.singletonList("Field error message"), errorDTO.getDetails());

    }

    @Test
    void testRuntimeError() {  // $$
        
        // Arrange
        MockitoAnnotations.openMocks(this);
        when(runtimeException.getCode()).thenReturn("RUNTIME_ERROR_CODE");
        when(runtimeException.getMessage()).thenReturn("Runtime error message");
        when(runtimeException.getDetails()).thenReturn(Collections.singletonList("Error details"));

        // Act
        ErrorDTO errorDTO = errorHandler.runtimeError(runtimeException);

        // Assert
        assertEquals("RUNTIME_ERROR_CODE", errorDTO.getCode());
        assertEquals("Runtime error message", errorDTO.getMessage());
        assertEquals(Collections.singletonList("Error details"), errorDTO.getDetails());

    }

    @Test
    void testUnknownError() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String errorMessage = "Unexpected error message";

        // Act
        ErrorDTO errorDTO = errorHandler.unknownError(genericException);

        // Assert
        assertEquals("ERROR_UNKNOWN", errorDTO.getCode());
        assertEquals("Ocurrió un error inesperado...", errorDTO.getMessage());
        assertEquals(null, errorDTO.getDetails());

    }

    private BindingResult mockBindingResult(String defaultMessage) {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", defaultMessage);

        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        return bindingResult;
    }

}
