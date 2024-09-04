package com.example.goeat_api.controller;

import com.example.goeat_api.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {


    @Autowired
    ClientService clientService;

    @GetMapping("/login")
    public ResponseEntity<String> loginClient(@RequestParam String email, @RequestParam String password){
        boolean isRegistred = clientService.loginClient(email, password);

        if(isRegistred) {
            return ResponseEntity.ok("Logado!");
        } else {
            return ResponseEntity.ok("Usuário incorreto ou inexistente!");
        }
    }
}
