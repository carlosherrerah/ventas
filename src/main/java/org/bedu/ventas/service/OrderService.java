package org.bedu.ventas.service;

import java.util.List;
import java.util.Optional;

import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.mapper.OrderMapper;
import org.bedu.ventas.model.Order;
import org.bedu.ventas.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDTO> findAll(){
        List<Order> data = orderRepository.findAll();
        return data.stream().map(orderMapper::toDTO).toList();
    }
    public OrderDTO findById(long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.isPresent() ? optionalOrder.map(orderMapper::toDTO).get() : new OrderDTO();
    }
}
