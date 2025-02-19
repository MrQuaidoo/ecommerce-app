package com.ecommerce.order.service;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for OrderService.
 * Ensures that the business logic for handling orders functions correctly.
 */
class OrderServiceTest {

    // Mocked repository for database operations
    @Mock
    private OrderRepository orderRepository;

    // Mocked order service
    @InjectMocks
    private OrderService orderService;

    /**
     * Initializes mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests that getOrdersByUserId correctly retrieves orders belonging to a specific user.
     */
    @Test
    void getOrdersByUserIdShouldReturnUserOrders() {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setUserId(2L);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setUserId(2L);

        when(orderRepository.findByUserId(2L)).thenReturn(Arrays.asList(order1, order2));

        List<Order> userOrders = orderService.getOrdersByUserId(2L);

        // Assertions
        assertEquals(2, userOrders.size(), "Should return two orders for user ID 2");
        verify(orderRepository, times(1)).findByUserId(2L);
    }

    /**
     * Tests that createOrder correctly saves and returns the order.
     */
    @Test
    void createOrderShouldSaveAndReturnOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setUserId(2L);

        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.createOrder(order);

        // Assertions
        assertNotNull(savedOrder, "Saved order should not be null");
        assertEquals(1L, savedOrder.getId(), "Order ID should be 1");
        verify(orderRepository, times(1)).save(order);
    }

    /**
     * Tests that getAllOrders correctly retrieves all orders from the repository.
     */
    @Test
    void getAllOrdersShouldReturnListOfOrders() {
        Order order1 = new Order();
        order1.setId(1L);

        Order order2 = new Order();
        order2.setId(2L);

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();

        // Assertions
        assertEquals(2, orders.size(), "Should return two orders");
        verify(orderRepository, times(1)).findAll();
    }

    /**
     * Tests that updateOrderStatus modifies the order status and saves the updated order.
     */
    @Test
    void updateOrderStatusShouldModifyOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setUserId(2L);
        order.setStatus("Pending");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateOrderStatus(1L, "Shipped");

        // Assertions
        assertNotNull(updatedOrder, "Updated order should not be null");
        assertEquals("Shipped", updatedOrder.getStatus(), "Order status should be updated to 'Shipped'");
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(order);
    }

    /**
     * Tests that updateOrderStatus returns null if the order is not found.
     */
    @Test
    void updateOrderStatusShouldReturnNullIfOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Order updatedOrder = orderService.updateOrderStatus(1L, "Shipped");

        // Assertions
        assertNull(updatedOrder, "Should return null when order is not found");
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, never()).save(any(Order.class));
    }
}
