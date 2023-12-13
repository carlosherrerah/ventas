package org.bedu.ventas.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderDTO {
    @Schema(
        description = "Empleado que realizo la orden de compra"
    )
    @NotNull
    @Min(1)
    private long employeeid;

    @Schema(description = "Fecha de orden de compra", example = "2023-12-02")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date orderdate;
}
