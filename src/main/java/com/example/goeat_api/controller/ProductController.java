package com.example.goeat_api.controller;

import com.example.goeat_api.entities.Product;
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


    public final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listAllProductsByMenuId(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.listAllProductsByMenuId(id));
    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody Product request){

        try {
            Product savedProduct = productService.registerProduct(request);
            return ResponseEntity.ok(savedProduct);
        } catch (IllegalArgumentException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
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


