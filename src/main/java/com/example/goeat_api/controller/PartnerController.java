package com.example.goeat_api.controller;

import com.example.goeat_api.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    PartnerService partnerService;

    @GetMapping("/login")
    public ResponseEntity<String> loginPartner(@RequestParam String email, @RequestParam String password){
        boolean isRegistered = partnerService.loginPartner(email, password);
        if(isRegistered){
            return ResponseEntity.ok("Parceiro logado!");
        } else {
            return ResponseEntity.ok("Parceiro incorreto ou inexistente!");
        }
    }
}
