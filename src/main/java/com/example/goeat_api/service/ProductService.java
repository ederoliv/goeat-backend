package com.example.goeat_api.service;

import com.example.goeat_api.entities.Product;
import com.example.goeat_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

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
            throw new IllegalArgumentException("produto já existe!");
        } else {
            return productRepository.save(product);
        }
    }

    public Boolean deleteProduct(UUID id){
        Product product = productRepository.findById(id).get();

        productRepository.delete(product);

        Optional<Product> existingProduct = productRepository.findById(id);

        return existingProduct.isEmpty();
    }

    public Optional<List<Product>> listAllProductsByMenuId(UUID menuId){
        return productRepository.findProductsByMenuId(menuId);
    }
}