package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.product.ProductByMenuIdRequestDTO;
import com.example.goeat_api.DTO.product.ProductsResponseDTO;
import com.example.goeat_api.entities.Product;
import com.example.goeat_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {


    public final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listAllProductsByMenuId(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.listAllProductsByMenuId(id));
    }
}


