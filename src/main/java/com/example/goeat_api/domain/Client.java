package com.example.goeat_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private  String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String phone;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;

}
