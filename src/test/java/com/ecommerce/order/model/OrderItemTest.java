package com.ecommerce.order.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the OrderItem model.
 * Ensures correct creation and management of OrderItem objects.
 */
public class OrderItemTest {

    /**
     * Tests the creation of an OrderItem object, including its properties.
     */
    @Test
    void testOrderItemCreation() {
        // Create a new OrderItem instance and set its properties
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setProductId(200L);
        orderItem.setQuantity(5);

        // Assertions to verify correct OrderItem properties
        assertNotNull(orderItem, "OrderItem object should not be null");
        assertEquals(1L, orderItem.getId(), "OrderItem ID should be 1");
        assertEquals(200L, orderItem.getProductId(), "Product ID should be 200");
        assertEquals(5, orderItem.getQuantity(), "Quantity should be 5");
    }
}