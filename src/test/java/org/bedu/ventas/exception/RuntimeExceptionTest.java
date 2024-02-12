package org.bedu.ventas.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class RuntimeExceptionTest {

    @Test
    void testRuntimeException() {
        // Arrange
        String code = "NotFound";
        String message = "Test Exception";
        Object details = new Object();

        // Act
        RuntimeException exception = new RuntimeException(code, message, details);

        // Assert
        assertNotNull(exception);
        assertEquals(code, exception.getCode());
        assertEquals(message, exception.getMessage());
        assertEquals(details, exception.getDetails());
    }

}
