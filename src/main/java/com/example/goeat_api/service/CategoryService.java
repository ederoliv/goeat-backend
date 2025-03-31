package com.example.goeat_api.service;

import com.example.goeat_api.DTO.category.CategoryRequestDTO;
import com.example.goeat_api.DTO.category.CategoryResponseDTO;
import com.example.goeat_api.entities.Category;
import com.example.goeat_api.entities.Menu;
import com.example.goeat_api.repository.CategoryRepository;
import com.example.goeat_api.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;

    public CategoryResponseDTO registerCategory(UUID menuId, CategoryRequestDTO request) {
        // Valida existência do menu
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu não encontrado"));

        // Cria a nova categoria associada ao menu
        Category newCategory = new Category();
        newCategory.setName(request.name());
        newCategory.setMenu(menu); // Associa ao menu do path

        Category savedCategory = categoryRepository.save(newCategory);

        return new CategoryResponseDTO(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getMenu().getId() // Retorna o menuId para confirmação
        );
    }

    public void deleteCategory(UUID menuId, Long categoryId) {
        // 1. Verifica se o menu existe
        if (!menuRepository.existsById(menuId)) {
            throw new EntityNotFoundException("Menu não encontrado com ID: " + menuId);
        }

        // 2. Busca a categoria e verifica se pertence ao menu
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + categoryId));

        if (!category.getMenu().getId().equals(menuId)) {
            throw new IllegalArgumentException("Categoria não pertence ao menu especificado");
        }

        // 4. Deleta a categoria
        categoryRepository.delete(category);
    }

    public Optional<List<Category>> listAllCategoriesByMenuId(UUID menuId) {
        return categoryRepository.findAllCategoriesByMenuId(menuId);
    }
}
