package com.ecommerce.order.repository;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.order.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit test for the OrderRepository.
 * Ensures that database interactions for the Order entity work correctly.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Tests the findByUserId method.
     * Ensures that orders belonging to a specific user are retrieved correctly.
     */
    @Test
    void testFindByUserId() {
        // Create and save a new order for user with ID 1
        Order order = new Order();
        order.setUserId(1L);
        order.setStatus("PENDING");
        orderRepository.save(order);

        // Retrieve orders for user with ID 1
        List<Order> orders = orderRepository.findByUserId(1L);

        // Verify that the order was successfully retrieved
        assertFalse(orders.isEmpty(), "Orders list should not be empty");
        assertEquals(1L, orders.get(0).getUserId(), "User ID should match the saved order");
    }
}
