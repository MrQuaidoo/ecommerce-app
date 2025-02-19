package com.ecommerce.product.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit test for the Product model.
 * Ensures correct behavior of the Product entity.
 */
public class ProductTest {

    /**
     * Tests the creation of a Product object and validates its attributes.
     */
    @Test
    void testProductCreation() {
        // Create a new product and set its attributes
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("High performance laptop");
        product.setPrice(1299.99);
        product.setCategories(List.of("Electronics", "Computers"));

        // Validate that the product object is properly initialized
        assertNotNull(product, "Product object should not be null");
        assertEquals(1L, product.getId(), "Expected product ID to be 1");
        assertEquals("Laptop", product.getName(), "Expected product name to be 'Laptop'");
        assertEquals("High performance laptop", product.getDescription(), "Expected description to match");
        assertEquals(1299.99, product.getPrice(), "Expected price to be 1299.99");
        assertEquals(2, product.getCategories().size(), "Expected categories size to be 2");
    }
}
