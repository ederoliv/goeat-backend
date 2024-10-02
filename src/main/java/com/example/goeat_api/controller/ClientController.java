package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.ClientLoginRequestDTO;
import com.example.goeat_api.DTO.ClientLoginResponseDTO;
import com.example.goeat_api.domain.Client;
import com.example.goeat_api.service.ClientService;
import com.example.goeat_api.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {


    private final PartnerService partnerService;
    @Autowired
    ClientService clientService;

    public ClientController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody ClientLoginRequestDTO request) {
        boolean isRegistered = clientService.loginClient(request.email(), request.password());

        if(isRegistered) {

            String clientName = clientService.getClientName(request.email());
            String clientUUID = clientService.getClientUUID(request.email());

            ClientLoginResponseDTO clientLoginResponseDTO = new ClientLoginResponseDTO(clientName, clientUUID);

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
