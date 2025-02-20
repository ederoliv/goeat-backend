package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.category.CategoryRequestDTO;
import com.example.goeat_api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> registerCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        try {
            // Verifica se o DTO é válido
            if (categoryRequestDTO.menuId() == null || categoryRequestDTO.name() == null || categoryRequestDTO.name().isEmpty()) {
                return ResponseEntity.badRequest().body("menuId e name são obrigatórios.");
            }

            // Registra a categoria usando o serviço
            CategoryRequestDTO createdCategory = categoryService.registerCategory(categoryRequestDTO);

            // Retorna a resposta com status 201 Created e o DTO da categoria criada
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (IllegalArgumentException e) {
            // Retorna erro 400 Bad Request com a mensagem de exceção
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Retorna erro 500 Internal Server Error para exceções inesperadas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar a requisição.");
        }
    }
}