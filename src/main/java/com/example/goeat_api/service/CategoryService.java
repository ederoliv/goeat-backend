package com.example.goeat_api.service;

import com.example.goeat_api.DTO.category.CategoryRequestDTO;
import com.example.goeat_api.entities.Category;
import com.example.goeat_api.entities.Menu;
import com.example.goeat_api.repository.CategoryRepository;
import com.example.goeat_api.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;

    public CategoryRequestDTO registerCategory(CategoryRequestDTO categoryResquestDTO) {
        Menu menu = menuRepository.findById(categoryResquestDTO.menuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        Category category = new Category(categoryResquestDTO.name(), menu);
        Category savedCategory = categoryRepository.save(category);
        return convertToCategoryRequestDTO(savedCategory);
    }

    public CategoryRequestDTO convertToCategoryRequestDTO(Category category) {

        return new CategoryRequestDTO(
                category.getMenu().getId(),
                category.getName()
        );
    }

    public Category convertToCategory(CategoryRequestDTO categoryResquestDTO) {
        Category category = new Category();
        category.setName(categoryResquestDTO.name());
        // Aqui você precisa buscar o Menu pelo ID e setar no category
        // category.setMenu(menuRepository.findById(categoryRequestDTO.menuId()).orElseThrow(...));
        return category;
    }
}
