package com.example.goeat_api.DTO.partner;

import java.util.UUID;

public record PartnerDTO(UUID id, String name, String email, String cnpj, String phone) {
}
