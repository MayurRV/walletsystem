package com.jsp.walletsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.walletsystem.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
