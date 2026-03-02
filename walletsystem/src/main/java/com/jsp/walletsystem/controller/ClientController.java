package com.jsp.walletsystem.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.walletsystem.dto.OrderRequestDTO;
import com.jsp.walletsystem.entity.Order;
import com.jsp.walletsystem.service.OrderService;
import com.jsp.walletsystem.service.WalletService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final OrderService orderService;
    private final WalletService walletService;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(
            @RequestHeader("client-id") Long clientId,
            @Valid @RequestBody OrderRequestDTO request) {

        Order order = orderService.createOrder(clientId, request.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(
            @PathVariable Long id,
            @RequestHeader("client-id") Long clientId) {

        return ResponseEntity.ok(orderService.getOrder(id, clientId));
    }

    @GetMapping("/wallet/balance")
    public ResponseEntity<Double> getBalance(
            @RequestHeader("client-id") Long clientId) {

        return ResponseEntity.ok(walletService.getBalance(clientId));
    }
}
