package com.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Represents a Product entity in the e-commerce system.
 * Stores product details including name, description, price, and categories.
 */
@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key
    private Long id;

    // Name of the product
    private String name;

    // Description of the product
    private String description;

    // Price of the product
    private double price;

    // Defines a collection of categories the product belongs to
    @ElementCollection
    private List<String> categories;

}
