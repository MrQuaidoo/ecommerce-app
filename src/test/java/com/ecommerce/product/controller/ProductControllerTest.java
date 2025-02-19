package com.ecommerce.product.controller;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit test for ProductController.
 * Ensures the correct behavior of API endpoints for product operations.
 */
@SpringBootTest(classes = {EcommerceApplication.class})
@AutoConfigureMockMvc
class ProductControllerTest {

    // MockMvc for simulating HTTP requests
    @Autowired
    private MockMvc mockMvc;

    // Mocked ProductService
    @MockBean
    private ProductService productService;

    /**
     * Tests that the getAllProducts endpoint returns a list of products.
     */
    @Test
    void getAllProductsShouldReturnListOfProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setDescription("High performance laptop");
        product1.setPrice(1299.99);
        product1.setCategories(List.of("Electronics", "Computers"));

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Phone");
        product2.setDescription("Smartphone with latest features");
        product2.setPrice(699.99);
        product2.setCategories(List.of("Electronics", "Mobiles"));

        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].categories[0]").value("Electronics"))
                .andExpect(jsonPath("$[0].categories[1]").value("Computers"))
                .andExpect(jsonPath("$[0].price").value(1299.99))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Phone"))
                .andExpect(jsonPath("$[1].categories[0]").value("Electronics"))
                .andExpect(jsonPath("$[1].categories[1]").value("Mobiles"))
                .andExpect(jsonPath("$[1].price").value(699.99));

        verify(productService, times(1)).getAllProducts();
    }

    /**
     * Tests that getProductById endpoint returns the correct product details.
     */
    @Test
    void getProductByIdShouldReturnProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("High performance laptop");
        product.setPrice(1299.99);
        product.setCategories(List.of("Electronics", "Computers"));

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.categories[0]").value("Electronics"))
                .andExpect(jsonPath("$.categories[1]").value("Computers"))
                .andExpect(jsonPath("$.price").value(1299.99));

        verify(productService, times(1)).getProductById(1L);
    }

    /**
     * Tests that getRecommendedProducts endpoint returns a list of products based on category.
     */
    @Test
    void getRecommendedProductsShouldReturnListOfRecommendedProducts() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("High performance laptop");
        product.setPrice(1500.0);
        product.setCategories(List.of("Electronics", "Computers"));

        when(productService.getRecommendedProducts("Electronics")).thenReturn(Arrays.asList(product));

        mockMvc.perform(get("/api/products/recommendations").param("category", "Electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].categories[0]").value("Electronics"))
                .andExpect(jsonPath("$[0].categories[1]").value("Computers"))
                .andExpect(jsonPath("$[0].price").value(1500.0));

        verify(productService, times(1)).getRecommendedProducts("Electronics");
    }

    /**
     * Tests that createProduct endpoint saves and returns the new product.
     */
    @Test
    void createProductShouldSaveProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("High performance laptop");
        product.setPrice(1500.0);
        product.setCategories(List.of("Electronics", "Computers"));

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType("application/json")
                        .content("{\"name\":\"Laptop\",\"categories\":[\"Electronics\",\"Computers\"],\"price\":1500.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.categories[0]").value("Electronics"))
                .andExpect(jsonPath("$.categories[1]").value("Computers"))
                .andExpect(jsonPath("$.price").value(1500.0));

        verify(productService, times(1)).createProduct(any(Product.class));
    }
}