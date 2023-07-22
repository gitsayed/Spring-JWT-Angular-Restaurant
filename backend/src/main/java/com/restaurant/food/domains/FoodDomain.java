package com.restaurant.food.domains;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodDomain {

    private Integer id;
    private String name;
    private Integer categoryId;
    private String foodType;
    private Long promoPrice;
    private Long actualPrice;
    private Integer restaurantId;
}
