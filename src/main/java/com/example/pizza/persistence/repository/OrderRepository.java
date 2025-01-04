package com.example.pizza.persistence.repository;

import com.example.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {



}
