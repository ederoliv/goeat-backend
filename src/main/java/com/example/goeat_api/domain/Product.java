package com.example.goeat_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private int price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Menu menu;

    /*
    * category
    * preparationTime
    * menuId
    * createdAt
     */

}
