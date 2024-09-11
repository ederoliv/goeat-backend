package com.example.goeat_api.controller;

import com.example.goeat_api.domain.Product;
import com.example.goeat_api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("products")
    public List<Product> listAllProducts(){
        return productService.listAllProducts();
    }
}
