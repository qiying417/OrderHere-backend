package com.backend.OrderHere.controller.v1;

import com.backend.OrderHere.model.Ingredient;
import com.backend.OrderHere.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    @Operation(summary = "get all ingredients")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get ingredient by id")
    public Ingredient getIngredientById(@PathVariable Integer id) {
        return ingredientService.getIngredientById(id);
    }

    @PostMapping
    @Operation(summary = "add ingredient")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "upgrade ingredient, require authentication")
    public Ingredient updateIngredient(@PathVariable Ingredient ingredient) {
        return ingredientService.updateIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete ingredient, require role:merchant")
    public void deleteIngredient(@PathVariable Integer id) {
        ingredientService.deleteIngredient(id);
    }
}
