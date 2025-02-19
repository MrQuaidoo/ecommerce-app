package com.ecommerce.order.controller;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the OrderController.
 * Ensures that all API endpoints function correctly.
 */
@SpringBootTest(classes = {EcommerceApplication.class})
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderService orderService;

    /**
     * Tests the retrieval of all orders.
     * Verifies that the response contains the expected list of orders.
     */
    @Test
    void getAllOrdersShouldReturnListOfOrders() throws Exception {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setUserId(2L);
        order1.setStatus("Pending");

        Order order2 = new Order();
        order2.setId(2L);
        order2.setUserId(3L);
        order2.setStatus("Shipped");

        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(2))
                .andExpect(jsonPath("$[0].status").value("Pending"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].userId").value(3))
                .andExpect(jsonPath("$[1].status").value("Shipped"));

        verify(orderService, times(1)).getAllOrders();
    }

    /**
     * Tests retrieving orders by user ID.
     * Ensures that only the orders belonging to the given user are returned.
     */
    @Test
    void getOrdersByUserIdShouldReturnUserOrders() throws Exception {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setUserId(2L);
        order1.setStatus("Pending");

        when(orderService.getOrdersByUserId(2L)).thenReturn(Arrays.asList(order1));

        mockMvc.perform(get("/api/orders/user/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(2))
                .andExpect(jsonPath("$[0].status").value("Pending"));

        verify(orderService, times(1)).getOrdersByUserId(2L);
    }

    /**
     * Tests order creation.
     * Verifies that the new order is saved and returned with the expected attributes.
     */
    @Test
    void createOrderShouldSaveOrder() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setUserId(2L);
        order.setStatus("Pending");

        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/api/orders")
                        .contentType("application/json")
                        .content("{\"userId\":2,\"status\":\"Pending\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.status").value("Pending"));

        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    /**
     * Tests updating the order status.
     * Ensures that the order status is modified correctly.
     */
    @Test
    void updateOrderStatusShouldModifyOrder() throws Exception {
        Order updatedOrder = new Order();
        updatedOrder.setId(1L);
        updatedOrder.setUserId(2L);
        updatedOrder.setStatus("Shipped");

        when(orderService.updateOrderStatus(1L, "Shipped")).thenReturn(updatedOrder);

        mockMvc.perform(put("/api/orders/1")
                        .param("status", "Shipped"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.status").value("Shipped"));

        verify(orderService, times(1)).updateOrderStatus(1L, "Shipped");
    }
}