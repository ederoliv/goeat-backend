package com.example.goeat_api.controller;

import com.example.goeat_api.DTO.Order.OrderResponseDTO;
import com.example.goeat_api.DTO.Order.OrderStatusDTO;
import com.example.goeat_api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/status")
    public ResponseEntity<?> changeStatus(@RequestBody OrderStatusDTO orderStatusDTO) {
        OrderResponseDTO  orderResponseDTO = orderService.updateOrderStatus(orderStatusDTO.id(), orderStatusDTO.status());

        if(orderStatusDTO != null) {
            return ResponseEntity.ok(orderResponseDTO);
        } else {
            return ResponseEntity.badRequest().body(orderResponseDTO);
        }
    }
}
