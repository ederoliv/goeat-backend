package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.Order.OrderDTO;
import com.example.goeat_api.DTO.Order.OrderResponseDTO;
import com.example.goeat_api.DTO.partner.PartnerLoginRequestDTO;
import com.example.goeat_api.DTO.partner.PartnerLoginResponseDTO;
import com.example.goeat_api.entities.Partner;
import com.example.goeat_api.service.OrderService;
import com.example.goeat_api.service.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/partners")
public class PartnerController {


    private final PartnerService partnerService;

    private final OrderService orderService;


    @PostMapping("/login")
    public ResponseEntity<?> loginPartner(@RequestBody PartnerLoginRequestDTO request){
        Optional<Partner> partner = partnerService.loginPartner(request);

        if(partner.isPresent()){

            PartnerLoginResponseDTO partnerLoginResponseDTO = new PartnerLoginResponseDTO(partner.get().getName(), partner.get().getId());

            return ResponseEntity.ok(partnerLoginResponseDTO);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parceiro incorreto ou inexistente!");
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

    @PostMapping("/{id}/orders")
    public ResponseEntity<?> orderPartner(@PathVariable UUID id, @RequestBody OrderDTO orderDTO){


        OrderResponseDTO responseDTO = orderService.createOrder(orderDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<?> getOrderPartner(@PathVariable UUID id){

        List<OrderResponseDTO> responseDTOList = orderService.getAllOrdersByPartnerId(id);

        return ResponseEntity.ok(responseDTOList);
    }
}