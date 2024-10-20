package com.example.goeat_api.DTO.OrderItemDTO;

import java.util.UUID;

public record OrderItemDTO(UUID productId, int quantity) {
}
