package com.ecommerce.product.service;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for handling business logic related to products.
 * Provides methods to retrieve, create and recommend products.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // Injects the ProductRepository for database operations

    /**
     * Retrieves all products available in the system.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param id The ID of the product to be fetched.
     * @return The product if found, otherwise null.
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves recommended products based on a given category.
     *
     * @param category The category to filter products by.
     * @return A list of products belonging to the specified category.
     */
    public List<Product> getRecommendedProducts(String category) {
        return productRepository.findByCategoriesContaining(category);
    }

    /**
     * Creates a new product in the system.
     *
     * @param product The product object to be saved.
     * @return The newly created product.
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
