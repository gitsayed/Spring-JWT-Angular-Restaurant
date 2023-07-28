package com.restaurant.food.entities;

import com.restaurant.category.entities.CategoryEntity;
import com.restaurant.restaurant.entities.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "foods")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Long offerPrice;
    private Long actualPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;
    @ManyToOne
    @JoinColumn(name = "restaurant_oid")
    private RestaurantEntity restaurant;


}
