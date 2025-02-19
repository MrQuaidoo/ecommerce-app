package com.ecommerce.order.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an individual item in an order.
 * Stores product details and quantity for an order.
 */
@Entity
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto-generates the primary key
    private Long id;

    // The ID of the product being ordered
    private Long productId;

    // The quantity of this product in the order
    private int quantity;
}
