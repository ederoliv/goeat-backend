package com.example.goeat_api.DTO.Order;

import com.example.goeat_api.DTO.OrderItemDTO.OrderItemDTO;

import java.util.List;
import java.util.UUID;

public record OrderDTO(UUID clientId, List<OrderItemDTO> items) {
}
