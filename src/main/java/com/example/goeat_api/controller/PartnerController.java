package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.PartnerLoginRequestDTO;
import com.example.goeat_api.DTO.PartnerLoginResponseDTO;
import com.example.goeat_api.domain.Partner;
import com.example.goeat_api.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    PartnerService partnerService;

    @PostMapping("/login")
    public ResponseEntity<?> loginPartner(@RequestBody PartnerLoginRequestDTO request){
        boolean isRegistered = partnerService.loginPartner(request.email(), request.password());

        //só pra saber se a request tá chegando na api
        System.out.println(request.email());
        System.out.println(request.password());

        if(isRegistered){

            String partnerName = partnerService.getPartnerName(request.email());//recupera o nome do Partner
            String partnerUUID = partnerService.getPartnerUUID(request.email());//recupera o UUID do Partner

            PartnerLoginResponseDTO partnerLoginResponseDTO = new PartnerLoginResponseDTO(partnerName, partnerUUID);


            return ResponseEntity.ok(partnerLoginResponseDTO);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Parceiro incorreto ou inexistente!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPartner(@RequestBody Partner partner){

        try{
            Partner savedPartner = partnerService.registerPartner(partner);
            return ResponseEntity.ok(savedPartner);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}