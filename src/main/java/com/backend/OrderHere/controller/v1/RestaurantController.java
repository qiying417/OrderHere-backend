package com.backend.OrderHere.controller.v1;

import com.backend.OrderHere.dto.Restaurant.RestaurantCreateDTO;
import com.backend.OrderHere.dto.Restaurant.RestaurantGetDTO;
import com.backend.OrderHere.dto.Restaurant.RestaurantUpdateDTO;
import com.backend.OrderHere.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/public/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get all restaurants")
    public List<RestaurantGetDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping
    @Operation(summary = "Create restaurants")
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantGetDTO createRestaurant(@Valid @RequestBody RestaurantCreateDTO restaurantCreateDTO) {
        return restaurantService.createRestaurant(restaurantCreateDTO);
    }

    @GetMapping("/{restaurantId}")
    @Operation(summary = "get restaurants by ID")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantGetDTO getRestaurantById(@PathVariable Integer restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('merchant')")
    @Operation(summary = "update Restaurant, require Authentication")
    public RestaurantGetDTO updateRestaurantById(@PathVariable Integer restaurantId, @Valid @RequestBody RestaurantUpdateDTO restaurantUpdateDTO) {
        return restaurantService.updateRestaurantById(restaurantId, restaurantUpdateDTO);
    }

}
