package com.jsp.walletsystem.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.walletsystem.dto.WalletRequestDTO;
import com.jsp.walletsystem.service.WalletService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final WalletService walletService;

    @PostMapping("/wallet/credit")
    public ResponseEntity<String> credit(
            @Valid @RequestBody WalletRequestDTO request) {

        walletService.credit(request.getClientId(), request.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Wallet credited successfully");
    }

    @PostMapping("/wallet/debit")
    public ResponseEntity<String> debit(
            @Valid @RequestBody WalletRequestDTO request) {

        walletService.debit(request.getClientId(), request.getAmount());
        return ResponseEntity.ok("Wallet debited successfully");
    }
}
