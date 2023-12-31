package org.bedu.ventas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Schema(description = "Modelo CreateOrderDTO para crear una orden de compra")
@Data
public class CreateOrderDTO {
    @Schema(description = "Empleado que realizo la orden de compra")
    @NotNull
    @Min(1)
    private long employeeid;
}
