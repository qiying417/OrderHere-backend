package com.backend.OrderHere.repository;

import com.backend.OrderHere.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findByName(String name);

    
}
