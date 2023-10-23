package com.backend.OrderHere.service;

import com.backend.OrderHere.dto.DishCreateDTO;
import com.backend.OrderHere.dto.DishGetDTO;
import com.backend.OrderHere.dto.DishUpdateDTO;
import com.backend.OrderHere.exception.ResourceNotFoundException;
import com.backend.OrderHere.mapper.DishMapper;
import com.backend.OrderHere.model.Dish;
import com.backend.OrderHere.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    public List<DishGetDTO> getAllDishes() {
        return dishRepository.findAll().stream().map(dishMapper::fromDishToDishGetDTO).collect(Collectors.toList());
    }

    public DishGetDTO getDishById(Integer dishId) {
        return dishMapper.fromDishToDishGetDTO(dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("Dish not found")));
    }

    public DishGetDTO createDish(DishCreateDTO dishCreateDTO) {
        Dish dish = dishMapper.fromDishCreateDTOToDish(dishCreateDTO);
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.fromDishToDishGetDTO(savedDish);
    }

    public DishGetDTO updateDish(Integer dishId, DishUpdateDTO dishUpdateDTO) {
        Dish dish = dishMapper.fromDishUpdateDTOToDish(dishUpdateDTO);
        if (!dishRepository.existsById(dish.getDishId())) {
            throw new ResourceNotFoundException("Dish not found");
        }
        Dish updatedDish = dishRepository.save(dish);
        return dishMapper.fromDishToDishGetDTO(updatedDish);
    }

    public void deleteDish(Integer dishId) {
        if (!dishRepository.existsById(dishId)) {
            throw new ResourceNotFoundException("Dish not found");
        }
        dishRepository.deleteById(dishId);
    }

}
