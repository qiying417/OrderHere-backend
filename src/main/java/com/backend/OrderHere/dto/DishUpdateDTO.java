package com.backend.OrderHere.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DishUpdateDTO {
    private String dishName;
    private String dishDescription;
    private BigDecimal dishPrice;
    private String dishCategory;
    private String dishImageURL;
}
