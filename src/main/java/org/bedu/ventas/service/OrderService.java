package org.bedu.ventas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bedu.ventas.dto.CreateOrderDTO;
import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.dto.UpdateOrderDTO;
import org.bedu.ventas.exception.ExcepcionRecursoNoEncontrado;
import org.bedu.ventas.mapper.OrderMapper;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.model.Order;
import org.bedu.ventas.repository.EmployeeRepository;
import org.bedu.ventas.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private EmployeeRepository employeeRepository;
    private OrderMapper orderMapper;
    
    public OrderService(OrderRepository orderRepository, EmployeeRepository employeeRepository,
            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderDTO> findAll(){
        List<Order> data = orderRepository.findAll();
        return data.stream().map(orderMapper::toDTO).toList();
    }

    public OrderDTO findById(long orderId) throws ExcepcionRecursoNoEncontrado {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if(!optionalOrder.isPresent()){
            throw new ExcepcionRecursoNoEncontrado(""+ orderId);
        }
        return optionalOrder.stream().map(orderMapper::toDTO).toList().get(0);
    }
   
    public OrderDTO save(CreateOrderDTO order) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(order.getEmployeeid());
        Order newOrder = new Order();
        if (optionalEmployee.isPresent()) {
            newOrder.setOrderdate(new Date());
            newOrder.setEmployee(optionalEmployee.get());
            
            return orderMapper.toDTO(orderRepository.save(newOrder));
        } else {
            return null;
        }
    }
    public OrderDTO update(long orderId, UpdateOrderDTO data) {
        Optional<Order> result = orderRepository.findById(orderId);
        
        if (!result.isPresent()) {
            return null;
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(data.getEmployeeid());
        Order order = result.get();
        
        if (!optionalEmployee.isPresent()) {
            return null;
        }
        
        order.setOrderdate(data.getOrderdate());
        order.setEmployee(optionalEmployee.get());
        
        return orderMapper.toDTO(orderRepository.save(order));
    }
}
