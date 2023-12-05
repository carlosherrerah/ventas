package org.bedu.ventas.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderDTO {
    private long orderid;

    private EmployeeDTO employee;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderdate;
}
