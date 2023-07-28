package com.restaurant.category.controller;


import com.restaurant.category.domains.CategoryDomain;
import com.restaurant.category.entities.CategoryEntity;
import com.restaurant.category.exceptions.CategoryException;
import com.restaurant.category.service.CategoryService;
import com.restaurant.role.exceptions.RoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> findCategoryInfo(Pageable pageable,
                                          @RequestParam(required = false) Integer id,
                                          @RequestParam(required = false) String name
    ) {
        log.info("Fetching Category info.");
        Page<CategoryEntity> categoryEntityPage = categoryService.findCategoryInfo( pageable,id, name);
        return ResponseEntity.ok(categoryEntityPage);
    }


    @PostMapping
    public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDomain categoryDomain){
        log.info("Saving Category.");
        categoryService.saveCategory(categoryDomain);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDomain categoryDomain){
        log.info("Update Category.");
        if (id == null || id == 0) {
            log.error("Category Id can't be null or zero.");
            throw new CategoryException("Category Id can't be null or zero.");
        }
        categoryDomain.setId(id);
        categoryService.saveCategory(categoryDomain);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        log.info("Deleting Category.");
        if (id == null || id == 0) {
            log.error("Category Id can't be null or zero.");
            throw new RoleException("Category Id can't be null or zero.");
        }
        if(!categoryService.existsById(id)){
            log.error("Category not found by Id: {}", id);
            throw new RoleException("Category not found by Id: "+ id);
        }
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
