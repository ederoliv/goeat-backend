package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.client.ClientLoginRequestDTO;
import com.example.goeat_api.DTO.client.ClientLoginResponseDTO;
import com.example.goeat_api.entities.Client;
import com.example.goeat_api.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/clients")
public class ClientController {


    private final ClientService clientService;


    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody ClientLoginRequestDTO request) {
        Optional<Client> client = clientService.loginClient(request);

        if(client.isPresent()) {

            ClientLoginResponseDTO clientLoginResponseDTO = new ClientLoginResponseDTO(client.get().getName(), client.get().getId());

            return ResponseEntity.ok(clientLoginResponseDTO);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cliente incorreto ou inexistente!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody Client client){

        try{
            Client savedClient = clientService.registerClient(client);
            return ResponseEntity.ok(savedClient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
