package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Order;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, Long> {
    Order save(Order order);

}
