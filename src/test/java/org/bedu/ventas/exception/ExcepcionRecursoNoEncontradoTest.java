package org.bedu.ventas.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class ExcepcionRecursoNoEncontradoTest {

@Test
    void testExcepcionRecursoNoEncontrado() {
        // Arrange
        String message = "Recurso no encontrado";

        // Act
        ExcepcionRecursoNoEncontrado exception = new ExcepcionRecursoNoEncontrado(message);

        // Assert
        assertEquals("ERR_DATA_NOT_FOUND", exception.getCode());
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getDetails());

    }
}
