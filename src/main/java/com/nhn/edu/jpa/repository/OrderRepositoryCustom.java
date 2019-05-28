package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Order;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface OrderRepositoryCustom {
    List<Order> getOrdersWithAssociations();

}
