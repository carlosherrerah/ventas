package org.bedu.ventas.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.bedu.ventas.dto.CreateOrderDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.dto.UpdateOrderDTO;
import org.bedu.ventas.exception.ExcepcionRecursoNoEncontrado;
import org.bedu.ventas.mapper.OrderMapper;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.model.Order;
import org.bedu.ventas.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository repository;

    @Autowired
    private OrderService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should find a list of orders")
    void testFindAll() {
        Random random = new Random();
        
        List<Order> data = new LinkedList<>();        
        for (int i = 0; i < 10; i++) {
            Order testOrder = new Order();
            testOrder.setOrderid(i+1);
            testOrder.setOrderdate(new Date());
            testOrder.setEmployee(new Employee());
            data.add(testOrder);
        }
        when(repository.findAll()).thenReturn(data);

        List<OrderDTO> result = service.findAll();
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertInstanceOf(
            OrderDTO.class, 
            result.get(random.nextInt(10 - 1 + 1) + 1)
        );
        assertInstanceOf(
            EmployeeDTO.class, 
            result.get(random.nextInt(10 - 1 + 1) + 1).getEmployee()
        );
        assertEquals(data.get(0).getOrderid(), 1);
    }

    @Test
    @DisplayName("Service should find an order with an id")
    public void testFindById() throws ExcepcionRecursoNoEncontrado{
        Employee employee = new Employee(20,"Gonzalez","Antonio",new Date(),new Date(),null);
        OrderDTO orderDTO = new OrderDTO();
        Order order = new Order();
        OrderMapper orderMapperMock = mock(OrderMapper.class);
        OrderService orderService = new OrderService(repository, null, orderMapperMock);

        Optional<Order> orderOptional = Optional.of(order);
        order.setOrderid(2);
        order.setEmployee(employee);
        orderDTO.setOrderid(2);
        orderDTO.setEmployee(null);


        when(repository.findById(anyLong())).thenReturn(orderOptional);
        when(orderMapperMock.toDTO(any())).thenReturn(orderDTO);

        OrderDTO orderDTOResult = orderService.findById(20);

        assertEquals(orderDTO, orderDTOResult);
    }

    @Test
    @DisplayName("Service should save an order")
    void saveTest() {
        CreateOrderDTO dto = new CreateOrderDTO();

        dto.setEmployeeid(1);

        Order order = new Order();

        order.setOrderid(100);
        order.setOrderdate(new Date());
        order.setEmployee(new Employee(
            1,
            "lastnameTest",
            "firstnameTest",
            new Date(),
            new Date(),
            null
        ));

        when(repository.save(any(Order.class))).thenReturn(order);

        OrderDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(order.getEmployee().getEmployeeid(), result.getEmployee().getEmployeeid());
        assertEquals(order.getOrderdate(), result.getOrderdate());
        assertEquals(order.getOrderid(), result.getOrderid());
    }

    @Test
    @DisplayName("Service should update an order")
    void testUpdate() {
        UpdateOrderDTO dto = new UpdateOrderDTO();

        dto.setEmployeeid(1);
        dto.setOrderdate(new Date());

        Order order = new Order();

        order.setOrderid(1);
        order.setOrderdate(new Date());
        order.setEmployee(new Employee(
            1,
            "lastnameTest",
            "firstnameTest",
            new Date(),
            new Date(),
            null
        ));

        when(repository.findById(anyLong())).thenReturn(Optional.of(order));

        service.update(1, dto);

        assertEquals(dto.getEmployeeid(), order.getEmployee().getEmployeeid());
        assertEquals(dto.getOrderdate(), order.getOrderdate());
        verify(repository, times(1)).save(order);
    }

    @Test
    @DisplayName("Service should throws an error if an order was not found")
    void testFindByIdWithError() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ExcepcionRecursoNoEncontrado.class, () -> service.findById(1000));
    }
}
