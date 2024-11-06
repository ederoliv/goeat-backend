package com.example.goeat_api.DTO.product;

import java.util.UUID;

public record ProductResponseDTO(
        String name,
        String description,
        int price,
        String imageUrl,
        UUID menuId
) {}
