package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.dto.OrderDto;
import com.nhn.edu.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends OrderRepositoryCustom, JpaRepository<Order, Long> {
    List<OrderDto> findAllBy();

}
