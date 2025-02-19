package com.ecommerce.product.repository;

import com.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for managing Product entities in the database.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds all products that belong to a specific category.
     *
     * @param category The category name to search for.
     * @return A list of products that belong to the specified category.
     */
    List<Product> findByCategoriesContaining(String category);
}
