package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.Order.OrderDTO;
import com.example.goeat_api.DTO.Order.OrderResponseDTO;
import com.example.goeat_api.DTO.partner.PartnerLoginRequestDTO;
import com.example.goeat_api.DTO.partner.PartnerLoginResponseDTO;
import com.example.goeat_api.DTO.partner.PartnerRequestDTO;
import com.example.goeat_api.DTO.partner.PartnerResponseDTO;
import com.example.goeat_api.DTO.product.ProductResponseDTO;
import com.example.goeat_api.entities.Partner;
import com.example.goeat_api.entities.Product;
import com.example.goeat_api.service.OrderService;
import com.example.goeat_api.service.PartnerService;
import com.example.goeat_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private final ProductService productService;

    private final OrderService orderService;


    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> getPartner(@PathVariable UUID id) {

        PartnerResponseDTO response = partnerService.getPartnerById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PartnerResponseDTO>> getAllPartners() {

        return ResponseEntity.ok(partnerService.listAllPartners());

    }

    @GetMapping("{id}/products")
    public ResponseEntity<?> getAllProductsByPartnerId(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.listAllProductsByMenuId(id));

    }

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

    @PostMapping
    public ResponseEntity<?> registerPartner(@RequestBody PartnerRequestDTO request){

        try{
           PartnerResponseDTO savedPartner = partnerService.registerPartner(request);
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