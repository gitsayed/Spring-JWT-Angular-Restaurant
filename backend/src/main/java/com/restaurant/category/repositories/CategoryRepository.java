package com.restaurant.category.repositories;


import com.restaurant.category.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor {


}
