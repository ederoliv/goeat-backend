package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.product.ProductDTO;
import com.example.goeat_api.entities.Menu;
import com.example.goeat_api.entities.Product;
import com.example.goeat_api.repository.MenuRepository;
import com.example.goeat_api.service.MenuService;
import com.example.goeat_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {


    private final MenuService menuService;
    private final ProductService productService;

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

                // Salvar o produto usando o service
                productService.registerProduct(product);

                return ResponseEntity.ok().build();

            }     else {
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
}