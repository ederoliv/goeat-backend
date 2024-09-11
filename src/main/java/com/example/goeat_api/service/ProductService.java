package com.example.goeat_api.service;

import com.example.goeat_api.domain.Product;
import com.example.goeat_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String searchProduct(String name){
        Optional<Product> existingProduct = productRepository.findByName(name);

        if (existingProduct.isPresent()){
            return existingProduct.get().getName();
        } else {

            return "produto não existe!";
        }
    }

    public List<Product> listAllProducts(){

        return productRepository.findAll();
    }

    public Product registerProduct(Product product){
        Optional<Product> existingProduct = productRepository.findByName(product.getName());

        if (existingProduct.isPresent()){
            throw new IllegalArgumentException("produto não existe!");
        } else {
            return productRepository.save(product);
        }
    }
}

