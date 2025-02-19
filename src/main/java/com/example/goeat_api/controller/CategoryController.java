package com.example.goeat_api.controller;

import com.example.goeat_api.entities.Category;
import com.example.goeat_api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> registerCategory(@RequestBody Category categoryDTO) {
        try {
            Category createdCategory = categoryService.registerCategory(categoryDTO);
            return ResponseEntity.ok(createdCategory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
