package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o inner join fetch o.customer as c "
           + " left join fetch o.orderItems as oi "
           + " inner join fetch oi.item as i")
    List<Order> getOrdersWithAssociations();

}
