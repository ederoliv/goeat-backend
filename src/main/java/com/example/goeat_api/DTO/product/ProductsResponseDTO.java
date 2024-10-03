package com.example.goeat_api.DTO.product;

import java.util.UUID;

public record ProductsResponseDTO(UUID id, String description, String image_url, String name, int price, UUID menuId) {
}
