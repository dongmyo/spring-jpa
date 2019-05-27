package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.entity.Order;
import com.nhn.edu.jpa.entity.OrderDetail;
import com.nhn.edu.jpa.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void doSomething() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());

        OrderDetail orderDetail1 = new OrderDetail("type1");
        orderDetail1.setDescription("order1-type1");
        orderDetail1.setOrder(order);

        OrderDetail orderDetail2 = new OrderDetail("type2");
        orderDetail2.setDescription("order1-type2");
        orderDetail2.setOrder(order);

        order.getDetails().add(orderDetail1);
        order.getDetails().add(orderDetail2);

        orderRepository.save(order);
    }

}
