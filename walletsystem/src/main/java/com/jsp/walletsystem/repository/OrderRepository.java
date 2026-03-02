package com.jsp.walletsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.walletsystem.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
