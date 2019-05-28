package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends OrderRepositoryCustom, JpaRepository<Order, Long> {
}
