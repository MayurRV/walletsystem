package com.jsp.walletsystem.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jsp.walletsystem.entity.Order;
import com.jsp.walletsystem.entity.OrderStatus;
import com.jsp.walletsystem.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WalletService walletService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public Order createOrder(Long clientId, Double amount) {

        // Step 1: Deduct wallet
        walletService.debit(clientId, amount);

        // Step 2: Create order
        Order order = new Order();
        order.setClientId(clientId);
        order.setAmount(amount);
        order.setStatus(OrderStatus.CREATED);

        order = orderRepository.save(order);

        // Step 3: Call fulfillment API
        String url = "https://jsonplaceholder.typicode.com/posts";

        Map<String, Object> request = new HashMap<>();
        request.put("userId", clientId);
        request.put("title", order.getId());

        try {
            Map response = restTemplate.postForObject(url, request, Map.class);

            order.setFulfillmentId(response.get("id").toString());
            order.setStatus(OrderStatus.FULFILLED);

        } catch (Exception e) {
            order.setStatus(OrderStatus.FAILED);
            throw new RuntimeException("Fulfillment service failed");
        }

        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId, Long clientId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getClientId().equals(clientId)) {
            throw new RuntimeException("Unauthorized access");
        }

        return order;
    }
}