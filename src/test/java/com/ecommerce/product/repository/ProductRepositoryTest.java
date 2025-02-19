package com.ecommerce.product.repository;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.product.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit test for the ProductRepository.
 * Ensures the correct behavior of database queries for product operations.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class ProductRepositoryTest {
    // Injects the ProductRepository for database operations
    @Autowired
    private ProductRepository productRepository;

    /**
     * Tests that findByCategoriesContaining returns a list of products
     * that belong to a specific category.
     */
    @Test
    void testFindByCategoriesContaining() {
        // Create a new product and set its attributes
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("High performance laptop");
        product.setPrice(1299.99);
        product.setCategories(List.of("Electronics", "Computers"));

        // Save the product to the database
        productRepository.save(product);

        // Retrieve products belonging to the "Electronics" category
        List<Product> products = productRepository.findByCategoriesContaining("Electronics");

        // Validate that the product list is not empty and contains the correct product
        assertFalse(products.isEmpty(), "Product list should not be empty");
        assertEquals("Laptop", products.get(0).getName(), "Expected product name to be 'Laptop'");
    }
}
