package com.backend.OrderHere.mapper.Dish;

import com.backend.OrderHere.dto.Dish.DishCreateDTO;
import com.backend.OrderHere.dto.Dish.DishGetDTO;
import com.backend.OrderHere.dto.Dish.DishUpdateDTO;
import com.backend.OrderHere.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {

    // 将Dish实体对象映射到DishGetDTO对象
    DishGetDTO fromDishToDishGetDTO(Dish dish);

    // 将DishCreateDTO对象映射到Dish实体对象
    Dish fromDishCreateDTOToDish(DishCreateDTO dishCreateDTO);

    // 将DishUpdateDTO对象映射到Dish实体对象
    Dish fromDishUpdateDTOToDish(DishUpdateDTO dishUpdateDTO);
}
