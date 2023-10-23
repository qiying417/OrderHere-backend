package com.backend.OrderHere.controller.v1;

import com.backend.OrderHere.dto.DishCreateDTO;
import com.backend.OrderHere.dto.DishUpdateDTO;
import com.backend.OrderHere.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    // 创建菜品
    @PostMapping
    public ResponseEntity<?> createDish(@RequestBody DishCreateDTO dishCreateDTO) {
        return ResponseEntity.ok(dishService.createDish(dishCreateDTO));
    }

    // 删除菜品
    @DeleteMapping("/{dishId}")
    public ResponseEntity<?> deleteDish(@PathVariable Integer dishId) {
        dishService.deleteDish(dishId);
        return ResponseEntity.ok().build();
    }

    // 更新菜品
    @PatchMapping("/{dishId}")
    public ResponseEntity<?> updateDish(@PathVariable Integer dishId, @RequestBody DishUpdateDTO dishUpdateDTO) {
        return ResponseEntity.ok(dishService.updateDish(dishId, dishUpdateDTO));
    }
}
