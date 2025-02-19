package com.ecommerce.product.controller;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products in the e-commerce system.
 * Provides endpoints to create, retrieve and recommend products.
 */
@RestController
@RequestMapping("/api/products") // Base URL for all product-related endpoints
public class ProductController {

    @Autowired
    private ProductService productService; // Injects the ProductService for business logic

    /**
     * Retrieves all products in the system.
     *
     * @return A list of all available products.
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param id The ID of the product to be fetched.
     * @return The product associated with the given ID.
     */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    /**
     * Retrieves recommended products based on a specific category.
     *
     * @param category The category for which recommendations are requested.
     * @return A list of recommended products.
     */
    @GetMapping("/recommendations")
    public List<Product> getRecommendedProducts(@RequestParam String category) {
        return productService.getRecommendedProducts(category);
    }

    /**
     * Creates a new product.
     *
     * @param product The product object containing details of the new product.
     * @return The newly created product.
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}
