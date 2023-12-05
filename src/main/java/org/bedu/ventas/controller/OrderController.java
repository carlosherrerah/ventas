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

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{orderId}")
    public OrderDTO findById(@PathVariable long orderId) {
        return orderService.findById(orderId);
    }
}
