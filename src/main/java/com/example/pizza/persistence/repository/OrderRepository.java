package com.example.pizza.persistence.repository;

import com.example.pizza.persistence.entity.OrderEntity;
import com.example.pizza.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    @Query(value = "SELECT po.id_order AS idOrder, " +
            "       c.name AS customerName, " +
            "       po.date AS orderDate, " +
            "       po.total AS orderTotal, " +
            "       GROUP_CONCAT(p.name) AS pizzaNames " +
            "FROM pizza_order po " +
            "JOIN customer c ON po.id_customer = c.id_customer " +
            "JOIN order_item oi ON po.id_order = oi.id_order " +
            "JOIN pizza p ON oi.id_pizza = p.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, c.name, po.date, po.total", nativeQuery = true)
    OrderSummary findSummary(int orderId);
}
