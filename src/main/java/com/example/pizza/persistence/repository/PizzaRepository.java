package com.example.pizza.persistence.repository;

import com.example.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

}
