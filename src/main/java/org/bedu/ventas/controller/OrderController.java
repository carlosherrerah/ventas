package org.bedu.ventas.controller;

import java.util.List;

import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoints de ordenes de compra", description = "CRUD de ordenes de compra")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @Operation(summary = "Obtiene una lista de toda las ordenes de compra")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @Operation(summary = "Obtiene una orden de compra según su ID")
    @GetMapping("/{orderId}")
    public OrderDTO findById(@PathVariable long orderId) {
        return orderService.findById(orderId);
    }
}
