package com.ecommerce.order.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit test class for the Order model.
 * Ensures correct creation and management of Order and OrderItem objects.
 */
public class OrderTest {

    /**
     * Tests the creation of an Order object, including its properties and associated OrderItems.
     */
    @Test
    void testOrderCreation() {
        // Create a new order instance and set its properties
        Order order = new Order();
        order.setId(1L);
        order.setUserId(1L);
        order.setStatus("PENDING");

        // Create first order item
        OrderItem item1 = new OrderItem();
        item1.setId(1L);
        item1.setProductId(100L);
        item1.setQuantity(2);

        // Create second order item
        OrderItem item2 = new OrderItem();
        item2.setId(2L);
        item2.setProductId(101L);
        item2.setQuantity(1);

        // Associate the order items with the order
        order.setOrderItems(List.of(item1, item2));

        // Assertions to verify correct order properties
        assertNotNull(order, "Order object should not be null");
        assertEquals(1L, order.getId(), "Order ID should be 1");
        assertEquals(1L, order.getUserId(), "User ID should be 1");
        assertEquals("PENDING", order.getStatus(), "Order status should be 'PENDING'");
        assertEquals(2, order.getOrderItems().size(), "Order should contain 2 items");
    }
}
