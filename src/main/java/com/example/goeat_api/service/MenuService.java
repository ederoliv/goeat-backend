package com.example.goeat_api.service;

import com.example.goeat_api.entities.Menu;
import com.example.goeat_api.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

    public final MenuRepository menuRepository;

    public Menu findById(UUID id) {
        return  menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));
    }



}
