package com.nhn.edu.jpa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhn.edu.jpa.dto.OrderDto;
import com.nhn.edu.jpa.entity.Customer;
import com.nhn.edu.jpa.entity.Item;
import com.nhn.edu.jpa.entity.Order;
import com.nhn.edu.jpa.entity.OrderItem;
import com.nhn.edu.jpa.repository.CustomerRepository;
import com.nhn.edu.jpa.repository.ItemRepository;
import com.nhn.edu.jpa.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;


    public OrderService(OrderRepository orderRepository,
                        ItemRepository itemRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }


    @Transactional
    public void setUp() {
        Item apple = new Item();
        apple.setItemName("apple");
        apple.setItemPrice(100L);
        apple = itemRepository.save(apple);

        Item orange = new Item();
        orange.setItemName("orange");
        orange.setItemPrice(200L);
        orange = itemRepository.save(orange);

        Item banana = new Item();
        banana.setItemName("banana");
        banana.setItemPrice(300L);
        banana = itemRepository.save(banana);

        Customer customer1 = new Customer();
        customer1.setCustomerName("customer1");
        customer1 = customerRepository.save(customer1);

        OrderItem orderItem1_1 = new OrderItem();
        orderItem1_1.setItem(apple);
        orderItem1_1.setQuantity(10L);

        OrderItem orderItem1_2 = new OrderItem();
        orderItem1_2.setItem(banana);
        orderItem1_2.setQuantity(2L);

        Order order1_1 = new Order();
        order1_1.setCustomer(customer1);
        order1_1.setOrderDate(LocalDateTime.now());
        order1_1.setOrderItems(Arrays.asList(orderItem1_1, orderItem1_2));
        orderRepository.save(order1_1);

        Customer customer2 = new Customer();
        customer2.setCustomerName("customer2");
        customer2 = customerRepository.save(customer2);

        OrderItem orderItem2_1 = new OrderItem();
        orderItem2_1.setItem(orange);
        orderItem2_1.setQuantity(3L);

        Order order2_1 = new Order();
        order2_1.setCustomer(customer2);
        order2_1.setOrderDate(LocalDateTime.now());
        order2_1.setOrderItems(Collections.singletonList(orderItem2_1));
        orderRepository.save(order2_1);

        OrderItem orderItem2_2 = new OrderItem();
        orderItem2_2.setItem(banana);
        orderItem2_2.setQuantity(5L);

        Order order2_2 = new Order();
        order2_2.setCustomer(customer2);
        order2_2.setOrderDate(LocalDateTime.now());
        order2_2.setOrderItems(Collections.singletonList(orderItem2_2));
        orderRepository.save(order2_2);
    }

    public void getOne() {
        orderRepository.findById(1L);
    }

    public void getMulti() {
        orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Item> getMultiWithOrderItems() {
        return orderRepository.getOrdersWithAssociations()
                              .stream()
                              .map(Order::getOrderItems)
                              .flatMap(Collection::stream)
                              .map(OrderItem::getItem)
                              .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public String getOrdersAsJson() throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter()
                                 .writeValueAsString(orderRepository.findAllBy());
    }

}
