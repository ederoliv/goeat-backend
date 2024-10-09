package com.example.goeat_api.controller;

import com.example.goeat_api.entities.Support;
import com.example.goeat_api.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/supports")
public class SupportController {

    public final SupportService supportService;

    @GetMapping("/{partnerId}")
    public ResponseEntity<?> listAllSupportsByPartnerId(@PathVariable UUID partnerId) {
        return ResponseEntity.status(HttpStatus.OK).body(supportService.listAllSupportsByPartnerId(partnerId));
    }

    @PostMapping
    public ResponseEntity<?> registerSupport(@RequestBody Support request) {

        try {
            Support savedSupport = supportService.registerSupport(request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
