package com.ecommerce.product.service;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for the ProductService.
 * Ensures correct behavior of business logic for product operations.
 */
class ProductServiceTest {
    // Mocked ProductRepository
    @Mock
    private ProductRepository productRepository;

    // Injects ProductService
    @InjectMocks
    private ProductService productService;

    /**
     * Initializes mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests that getAllProducts() returns a list of products.
     */
    @Test
    void getAllProductsShouldReturnListOfProducts() {
        // Create mock products
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Phone");

        // Mock the repository method call
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Call the service method
        List<Product> products = productService.getAllProducts();

        // Validate the results
        assertEquals(2, products.size(), "Expected list size to be 2");
        verify(productRepository, times(1)).findAll(); // Verify repository interaction
    }

    /**
     * Tests that getProductById() returns the correct product when found.
     */
    @Test
    void getProductByIdShouldReturnProduct() {
        // Create a mock product
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        // Mock the repository method call
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Call the service method
        Product foundProduct = productService.getProductById(1L);

        // Validate the results
        assertNotNull(foundProduct, "Product should not be null");
        assertEquals("Laptop", foundProduct.getName(), "Expected product name to be 'Laptop'");
        verify(productRepository, times(1)).findById(1L); // Verify repository interaction
    }

    /**
     * Tests that getProductById() returns null if the product is not found.
     */
    @Test
    void getProductByIdShouldReturnNullIfNotFound() {
        // Mock the repository method call to return empty
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Product foundProduct = productService.getProductById(1L);

        // Validate the results
        assertNull(foundProduct, "Expected null when product is not found");
        verify(productRepository, times(1)).findById(1L); // Verify repository interaction
    }

    /**
     * Tests that getRecommendedProducts() filters products by category.
     */
    @Test
    void getRecommendedProductsShouldReturnFilteredProducts() {
        // Create a mock product
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setCategories(Arrays.asList("Electronics", "Computers"));

        // Mock the repository method call
        when(productRepository.findByCategoriesContaining("Electronics")).thenReturn(Arrays.asList(product));

        // Call the service method
        List<Product> recommendedProducts = productService.getRecommendedProducts("Electronics");

        // Validate the results
        assertEquals(1, recommendedProducts.size(), "Expected list size to be 1");
        assertEquals("Laptop", recommendedProducts.get(0).getName(), "Expected product name to be 'Laptop'");

        // Verify repository interaction
        verify(productRepository, times(1)).findByCategoriesContaining("Electronics");
    }

    /**
     * Tests that createProduct() saves and returns the created product.
     */
    @Test
    void createProductShouldSaveAndReturnProduct() {
        // Create a mock product
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        // Mock the repository method call
        when(productRepository.save(product)).thenReturn(product);

        // Call the service method
        Product savedProduct = productService.createProduct(product);

        // Validate the results
        assertNotNull(savedProduct, "Saved product should not be null");
        assertEquals(1L, savedProduct.getId(), "Expected product ID to be 1");
        assertEquals("Laptop", savedProduct.getName(), "Expected product name to be 'Laptop'");

        // Verify repository interaction
        verify(productRepository, times(1)).save(product);
    }
}