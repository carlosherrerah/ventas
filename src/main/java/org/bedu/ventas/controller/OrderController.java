package org.bedu.ventas.controller;

import java.util.List;

import org.bedu.ventas.dto.CreateOrderDTO;
import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.dto.UpdateOrderDTO;
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
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Endpoints de ordenes de compra", description = "CRUD de ordenes de compra")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @Operation(summary = "Obtiene una lista de toda las ordenes de compra", description = "No requiere parámetros")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @Operation(summary = "Obtiene una orden de compra según su ID", description = "Agrega el id de la orden de compra a la URL")
    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO findById(@PathVariable long orderId) {
        return orderService.findById(orderId);
    }

    @Operation(summary = "Guarda una orden de compra")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO save(@Valid @RequestBody CreateOrderDTO entity) {        
        return orderService.save(entity);
    }

    @Operation(summary = "Actualiza una orden de compra")
    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public OrderDTO update(@PathVariable long orderId, @Valid @RequestBody UpdateOrderDTO order){
        return orderService.update(orderId, order);
    }
}
