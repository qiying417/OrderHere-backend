package com.backend.OrderHere.controller.v1;

import com.backend.OrderHere.dto.Category.CategoryGetDto;
import com.backend.OrderHere.dto.PagingDto;
import com.backend.OrderHere.dto.dish.DishGetDto;
import com.backend.OrderHere.service.CategoryService;
import com.backend.OrderHere.service.DishService;
import com.backend.OrderHere.service.enums.DishSort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.backend.OrderHere.util.SortHelper.getSortOrder;

@RestController
@RequestMapping("/v1/public/dish")
@RequiredArgsConstructor
@Validated
public class DishController {
    private final DishService dishService;

    @GetMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get dishes information")
    public PagingDto<List<DishGetDto>> getDishes(@PathVariable Integer restaurantId,
                                                                @RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "0") int size,
                                                                @RequestParam(defaultValue = "category") String sort,
                                                                @RequestParam(defaultValue = "asc") String order) {
        return dishService.getDishPageByRestaurantId(
                restaurantId,
                page,
                size,
                DishSort.getEnumByString(sort),
                getSortOrder(order)
        );
    }
    @GetMapping("/{restaurantId}/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get dishes by category")
    public List<DishGetDto> getDishesByCategory(@PathVariable Integer restaurantId,
                                                @PathVariable Integer categoryId) {
        return dishService.getDishByCategory(restaurantId, categoryId);
    }
}
