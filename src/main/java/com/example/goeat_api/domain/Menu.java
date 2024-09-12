package com.example.goeat_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @Column(name = "partner_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private String description;

    @OneToMany(mappedBy = "menu")
    private List<Product> products;


}
