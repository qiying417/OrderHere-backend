package com.backend.OrderHere.controller.v1;

import com.backend.OrderHere.dto.Category.CategoryGetDto;
import com.backend.OrderHere.dto.Category.CategoryPostDto;
import com.backend.OrderHere.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/public/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get categories")
    public List<CategoryGetDto> getCategories(@PathVariable Integer restaurantId) {
        return categoryService.getCategoryByRestaurantId(restaurantId);
    }

    @PostMapping("/{restaurantId}")
    @Operation(summary = "create category")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryGetDto createCategory(@Valid @RequestBody CategoryPostDto categoryPostDto) {
        return categoryService.createCategory(categoryPostDto);
    }
}
