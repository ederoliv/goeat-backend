package com.example.goeat_api.DTO.client;

import java.util.UUID;

public record ClientLoginResponseDTO(String name, UUID clientId) {
}
