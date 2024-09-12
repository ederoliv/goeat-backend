package com.example.goeat_api.controller;

import com.example.goeat_api.domain.Product;
import com.example.goeat_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("products")
    public List<Product> listAllProducts(){
        return productService.listAllProducts();
    }
}
