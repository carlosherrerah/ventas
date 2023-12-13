package org.bedu.ventas.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Orden de compra")
@Data
public class OrderDTO {
    @Schema(description = "Identificador de la orden de compra", example = "2")
    private long orderid;

    @Schema(description = "Empleado que realizo la orden de compra")
    private EmployeeDTO employee;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Fecha de orden de compra", pattern = "yyyy-MM-dd", example = "2023-12-02", format = "date-time")
    private Date orderdate;
}
