package org.bedu.ventas.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.exception.ExcepcionRecursoNoEncontrado;
import org.bedu.ventas.mapper.EmployeeMapper;
import org.bedu.ventas.mapper.OrderMapper;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.model.Order;
import org.bedu.ventas.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.bedu.ventas.service.OrderService;
import org.mockito.Mockito;


public class OrderServiceTest {

    OrderRepository orderRepositoryMock = mock(OrderRepository.class);
    OrderMapper orderMapperMock = mock(OrderMapper.class);
    Employee employee = new Employee(20,"Gonzalez","Antonio",new Date(),new Date(),null);
    OrderDTO orderDTO = new OrderDTO();
    Order order = new Order();
    OrderService orderService = new OrderService(orderRepositoryMock, null, orderMapperMock);
  
    @Test
    @DisplayName("findById should find with an id")
    public void findByIdSuccessTest() throws ExcepcionRecursoNoEncontrado{

        Optional<Order> orderOptional = Optional.of(order);
        order.setOrderid(2);
        order.setEmployee(employee);
        orderDTO.setOrderid(2);
        orderDTO.setEmployee(null);


        when(orderRepositoryMock.findById(anyLong())).thenReturn(orderOptional);
        when(orderMapperMock.toDTO(any())).thenReturn(orderDTO);

        OrderDTO orderDTOResult = orderService.findById(20);

        assertEquals(orderDTO, orderDTOResult);
    }

   /*  @Test
    @DisplayName("findById should NOT find with an id")
    public void findByIdSuccessTest() throws ExcepcionRecursoNoEncontrado{

        Optional<Order> orderOptional = Optional.of(order);
        order.setOrderid(2);
        order.setEmployee(employee);
        orderDTO.setOrderid(2);
        orderDTO.setEmployee(null);


        when(orderRepositoryMock.findById(anyLong())).thenReturn(null);
        when(orderMapperMock.toDTO(any())).thenReturn(orderDTO);

        OrderDTO orderDTOResult = orderService.findById(20);
        assertThrows(, ExcepcionRecursoNoEncontrado);
    }*/
    
}
