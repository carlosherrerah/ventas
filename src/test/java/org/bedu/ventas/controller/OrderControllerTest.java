package org.bedu.ventas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bedu.ventas.dto.CreateOrderDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.dto.UpdateOrderDTO;
import org.bedu.ventas.exception.ExcepcionRecursoNoEncontrado;
import org.bedu.ventas.model.Order;
import org.bedu.ventas.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderControllerTest {
    @MockBean
    private OrderService service;

    @Autowired
    private OrderController controller;
    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }
    @Test
    @DisplayName("GET /orders should return a list of orders")
    void testFindAll() throws ExcepcionRecursoNoEncontrado,ParseException{
        //Arrange test
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;
        List<OrderDTO> fakeData = new LinkedList<>();
        OrderDTO fakeOrder = new OrderDTO();
        fakeOrder.setOrderid(100);
        fakeOrder.setEmployee(new EmployeeDTO());
        fecha = formatDate.parse("2023-12-02");
        fakeOrder.setOrderdate(fecha);
        fakeData.add(fakeOrder);
        when(service.findAll()).thenReturn(fakeData);
        //Act
        List<OrderDTO> result = controller.findAll();
        //Assert
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(fakeOrder.getOrderid(), result.get(0).getOrderid());
        assertEquals(fakeOrder.getEmployee(), result.get(0).getEmployee());
        assertEquals(fakeOrder.getOrderdate(), result.get(0).getOrderdate());
    }
    @Test
    @DisplayName("GET /orders/{orderId} should return a empty list of orders")
    void emptyListTest(){
        List<OrderDTO> fakeData = new LinkedList<>();
        when(service.findAll()).thenReturn(Collections.emptyList());
        //Act
        List<OrderDTO> result = controller.findAll();
        //Assert
        assertEquals(Collections.emptyList(), result);
    }
    @Test
    @DisplayName("GET /orders should return a order")
    void testFindById() throws ExcepcionRecursoNoEncontrado,ParseException{
        OrderDTO fakeOrder = new OrderDTO();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;
        fecha = formatDate.parse("2023-12-02");
        fakeOrder.setOrderid(100);
        fakeOrder.setEmployee(new EmployeeDTO());
        fakeOrder.setOrderdate(fecha);
        Mockito.when(service.findById(100)).thenReturn(fakeOrder);
        OrderDTO result = controller.findById(100);
        assertNotNull(result);
        assertEquals(fakeOrder.getOrderid(), result.getOrderid());
        assertEquals(fakeOrder.getEmployee(), result.getEmployee());
        assertEquals(fakeOrder.getOrderdate(), result.getOrderdate());
    }

    @Test
    void testSave() throws ParseException{
        CreateOrderDTO dto = new CreateOrderDTO();
        dto.setEmployeeid(100);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=formatDate.parse("2023-12-02");
        OrderDTO orders= new OrderDTO();
        orders.setEmployee(new EmployeeDTO());
        orders.setOrderid(dto.getEmployeeid());
        orders.setOrderdate(fecha);
        when(service.save(any(CreateOrderDTO.class))).thenReturn(orders);
        OrderDTO result = controller.save(dto);
        assertNotNull(result);
        assertEquals(orders.getEmployee(), result.getEmployee());
        assertEquals(orders.getOrderid(), result.getOrderid());
        assertEquals(orders.getOrderdate(), result.getOrderdate());
        assertEquals(orders, result);
    }

    @Test
    void testUpdate() throws ParseException{
        UpdateOrderDTO dto = new UpdateOrderDTO();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=formatDate.parse("2023-12-12");
        dto.setEmployeeid(100);
        dto.setOrderdate(fecha);
        controller.update(100, dto);
        verify(service, times(1)).update(100, dto);
    }
}
