package com.ecommerce.order.repository;

import com.ecommerce.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for managing Order entities in the database.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds all orders placed by a specific user.
     *
     * @param userId The ID of the user whose orders need to be retrieved.
     * @return A list of orders associated with the given user ID.
     */
    List<Order> findByUserId(Long userId);
}
