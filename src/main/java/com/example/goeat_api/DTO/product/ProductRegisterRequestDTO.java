package com.example.goeat_api.DTO.product;

import java.util.UUID;

public record ProductRegisterRequestDTO(String name, String description, int price, UUID menu) {
}
