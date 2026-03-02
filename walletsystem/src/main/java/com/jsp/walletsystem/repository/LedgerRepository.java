package com.jsp.walletsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.walletsystem.entity.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {

}
