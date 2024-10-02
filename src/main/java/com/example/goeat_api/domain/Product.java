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

    //muitos produtos podem pertencer a um mesmo menu
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
