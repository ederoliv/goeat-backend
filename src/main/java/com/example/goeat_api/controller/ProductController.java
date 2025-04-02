package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.product.ProductDTO;
import com.example.goeat_api.entities.Category;
import com.example.goeat_api.entities.Menu;
import com.example.goeat_api.entities.Product;
import com.example.goeat_api.service.CategoryService;
import com.example.goeat_api.service.MenuService;
import com.example.goeat_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final MenuService menuService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listAllProductsByMenuId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listAllProductsByMenuId(id));
    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody ProductDTO productDTO) {
        try {
            Menu menu = menuService.findById(productDTO.menuId());

            if (menu != null) {
                // Criar um novo objeto Product
                Product product = new Product();

                product.setName(productDTO.name());
                product.setDescription(productDTO.description());
                product.setPrice(productDTO.price());
                product.setImageUrl(productDTO.imageUrl());
                product.setMenu(menu);

                // Associar a categoria, se fornecida
                if (productDTO.categoryId() != null) {
                    Category category = categoryService.findById(productDTO.categoryId());
                    if (category != null) {
                        product.setCategory(category);
                    }
                }

                // Salvar o produto usando o service
                productService.registerProduct(product);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable UUID id) {
        if(productService.deleteProduct(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO productDTO) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDTO);

            if (updatedProduct != null) {
                return ResponseEntity.ok(updatedProduct);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}