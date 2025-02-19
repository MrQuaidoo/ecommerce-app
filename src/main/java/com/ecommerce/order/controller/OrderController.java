package com.ecommerce.order.controller;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing orders in the e-commerce system.
 * Provides endpoints to create, retrieve and update orders.
 */
@RestController
@RequestMapping("/api/orders") // Base URL for all order-related endpoints
public class OrderController {

    @Autowired
    private OrderService orderService; // Injects the OrderService for business logic

    /**
     * Retrieves all orders in the system.
     *
     * @return A list of all orders.
     */
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Retrieves orders for a specific user.
     *
     * @param userId The ID of the user whose orders need to be fetched.
     * @return A list of orders associated with the given user.
     */
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    /**
     * Creates a new order.
     *
     * @param order The order object containing details of the new order.
     * @return The newly created order.
     */
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    /**
     * Updates the status of an existing order.
     *
     * @param orderId The ID of the order to be updated.
     * @param status  The new status of the order.
     * @return The updated order object.
     */
    @PutMapping("/{orderId}")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}
