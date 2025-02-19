package com.ecommerce.order.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Represents an Order entity in the e-commerce system.
 * Stores order details, including the user who placed the order and the associated order items.
 */
@Entity
@Getter
@Setter
@Table(name = "orders") // Maps this entity to the "orders" table in the database
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key
    private Long id;

    // The ID of the user who placed the order
    private Long userId;

    // The current status of the order (e.g., "PENDING", "SHIPPED", "DELIVERED")
    private String status;

    // Defines a one-to-many relationship with OrderItem
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems; // List of items included in this order
}
