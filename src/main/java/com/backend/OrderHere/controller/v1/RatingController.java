package com.backend.OrderHere.controller.v1;

import com.backend.OrderHere.dto.Rating.RatingGetDto;
import com.backend.OrderHere.dto.Rating.RatingPostDto;
import com.backend.OrderHere.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/public/rating")
@RequiredArgsConstructor
@Validated
public class RatingController {
    private final RatingService ratingService;

    @GetMapping("/{dishId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get Rating by dish id")
    public List<RatingGetDto> getRatingsByDishId(@PathVariable Integer dishId) {
        return ratingService.getRatingsByDishId(dishId);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get rating by user id")
    public List<RatingGetDto> getRatingsByUserId(@PathVariable Integer userId) {
        return ratingService.getRatingsByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create rating")
    public RatingGetDto createRating(@Valid @RequestBody RatingPostDto ratingPostDto) {
        return ratingService.createRating(ratingPostDto);
    }

    @DeleteMapping("/{ratingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete Rating, require role:merchant")
    public void deleteRating(@PathVariable Integer ratingId) {
        ratingService.deleteRating(ratingId);
    }
}
