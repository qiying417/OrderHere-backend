package com.backend.OrderHere.mapper;

import com.backend.OrderHere.dto.DishCreateDTO;
import com.backend.OrderHere.dto.DishGetDTO;
import com.backend.OrderHere.dto.DishUpdateDTO;
import com.backend.OrderHere.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {


    DishGetDTO fromDishToDishGetDTO(Dish dish);

    Dish fromDishCreateDTOToDish(DishCreateDTO dishCreateDTO);

    Dish fromDishUpdateDTOToDish(DishUpdateDTO dishUpdateDTO);
}
