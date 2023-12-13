package org.bedu.ventas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {

    private String code;
    private String message;
    private Object details;

}
