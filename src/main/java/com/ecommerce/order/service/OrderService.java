package com.ecommerce.order.service;

import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for handling business logic related to orders.
 * Provides methods to retrieve, create and update orders.
 */
@Service
public class OrderService {

    @Autowired
    // Injects the OrderRepository for database operations
    private OrderRepository orderRepository;

    /**
     * Retrieves all orders placed by a specific user.
     *
     * @param userId The ID of the user whose orders need to be fetched.
     * @return A list of orders associated with the given user.
     */
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    /**
     * Creates a new order in the system.
     *
     * @param order The order object to be saved.
     * @return The newly created order.
     */
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Retrieves all orders in the system.
     *
     * @return A list of all orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Updates the status of an existing order.
     *
     * @param orderId The ID of the order to be updated.
     * @param status  The new status of the order.
     * @return The updated order object or null if the order does not exist.
     */
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }
        return order;
    }
}