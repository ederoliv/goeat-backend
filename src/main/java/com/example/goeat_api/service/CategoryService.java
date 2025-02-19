package com.example.goeat_api.service;

import com.example.goeat_api.entities.Category;
import com.example.goeat_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category registerCategory(Category category) {

        return categoryRepository.save(category);

    }
}
