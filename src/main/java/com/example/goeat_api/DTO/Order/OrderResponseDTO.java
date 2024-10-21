package com.example.goeat_api.DTO.Order;

import com.example.goeat_api.entities.StatusType;

import java.util.UUID;

public record OrderResponseDTO(Long id, StatusType orderStatus, int totalPrice, UUID clientId, UUID partnerId) {
}
