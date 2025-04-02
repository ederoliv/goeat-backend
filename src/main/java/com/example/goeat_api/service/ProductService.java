package com.example.goeat_api.service;

import com.example.goeat_api.DTO.product.ProductDTO;
import com.example.goeat_api.entities.Category;
import com.example.goeat_api.entities.Menu;
import com.example.goeat_api.entities.Product;
import com.example.goeat_api.repository.CategoryRepository;
import com.example.goeat_api.repository.MenuRepository;
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
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public String searchProduct(String name){
        Optional<Product> existingProduct = productRepository.findByName(name);

        if (existingProduct.isPresent()){
            return existingProduct.get().getName();
        } else {
            return "produto não existe!";
        }
    }

    public Product registerProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(UUID id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setName(productDTO.name());
            product.setDescription(productDTO.description());
            product.setPrice(productDTO.price());
            product.setImageUrl(productDTO.imageUrl());

            if (productDTO.menuId() != null) {
                Optional<Menu> menu = menuRepository.findById(productDTO.menuId());
                menu.ifPresent(product::setMenu);
            }

            if (productDTO.categoryId() != null) {
                Optional<Category> category = categoryRepository.findById(productDTO.categoryId());
                category.ifPresent(product::setCategory);
            } else {
                product.setCategory(null);
            }

            return productRepository.save(product);
        }

        return null;
    }

    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }

    public Boolean deleteProduct(UUID id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        }

        return false;
    }

    public Optional<List<Product>> listAllProductsByMenuId(UUID menuId){
        return productRepository.findProductsByMenuId(menuId);
    }
}