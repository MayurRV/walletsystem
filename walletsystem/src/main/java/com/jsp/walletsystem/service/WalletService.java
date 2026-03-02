package com.jsp.walletsystem.service;

import org.springframework.stereotype.Service;

import com.jsp.walletsystem.entity.Ledger;
import com.jsp.walletsystem.entity.Wallet;
import com.jsp.walletsystem.repository.LedgerRepository;
import com.jsp.walletsystem.repository.WalletRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {

	private final WalletRepository walletRepository;
    private final LedgerRepository ledgerRepository;

    @Transactional
    public void credit(Long clientId, Double amount) {

        Wallet wallet = walletRepository.findById(clientId)
                .orElse(new Wallet(clientId, 0.0));

        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);

        ledgerRepository.save(new Ledger(null, clientId, amount, "CREDIT"));
    }

    @Transactional
    public void debit(Long clientId, Double amount) {

        Wallet wallet = walletRepository.findById(clientId)
                .orElseThrow(() ->new RuntimeException("Wallet does not exist for client: " + clientId));

        if (wallet.getBalance() < amount) {
        	throw new RuntimeException("Insufficient wallet balance");
        }

        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);

        ledgerRepository.save(new Ledger(null, clientId, amount, "DEBIT"));
    }

    public Double getBalance(Long clientId) {
        return walletRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"))
                .getBalance();
    }
	
}
