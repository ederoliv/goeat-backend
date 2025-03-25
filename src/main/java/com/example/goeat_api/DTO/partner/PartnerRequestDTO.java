package com.example.goeat_api.DTO.partner;

import java.util.UUID;

public record PartnerRequestDTO(String name, String email, String cnpj, String phone,String password) {
}
