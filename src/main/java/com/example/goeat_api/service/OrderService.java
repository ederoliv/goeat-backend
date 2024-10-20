package com.example.goeat_api.service;

import com.example.goeat_api.DTO.Order.OrderDTO;
import com.example.goeat_api.DTO.OrderItemDTO.OrderItemDTO;
import com.example.goeat_api.entities.*;
import com.example.goeat_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final ClientRepository clientRepository;

    private final PartnerRepository partnerRepository;

    private final OrderItemRepository orderItemRepository;


    public Order createOrder(OrderDTO orderDTO, UUID partnerId) {
        // 1. Buscar o cliente a partir do ID
        Client client = clientRepository.findById(orderDTO.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // 2. Buscar o parceiro a partir do ID
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        // 3. Criar o pedido
        Order order = new Order();
        order.setClient(client);
        order.setPartner(partner);
        order.setOrderStatus(StatusType.ESPERANDO);  // Status inicial

        // 4. Salvar o pedido no banco (sem itens por enquanto)
        order = orderRepository.save(order);

        // 5. Adicionar os itens ao pedido
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.items()) {
            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());

            // Adicione o item ao banco
            orderItems.add(orderItemRepository.save(orderItem));
        }

        // 6. Associar os itens ao pedido e atualizar o pedido
        order.setItems(orderItems);
        order.setTotalPrice(calculateTotalPrice(orderItems));  // Calcular o preço total
        return orderRepository.save(order);
    }

    // Método para calcular o preço total
    private int calculateTotalPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
