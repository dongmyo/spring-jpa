package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Order;
import com.nhn.edu.jpa.entity.QCustomer;
import com.nhn.edu.jpa.entity.QItem;
import com.nhn.edu.jpa.entity.QOrder;
import com.nhn.edu.jpa.entity.QOrderAttribute;
import com.nhn.edu.jpa.entity.QOrderItem;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport
        implements OrderRepositoryCustom {
    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> getOrdersWithAssociations() {
        QOrder order = QOrder.order;
        QCustomer customer = QCustomer.customer;
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;
        QOrderAttribute attribute = QOrderAttribute.orderAttribute;

        return from(order)
                .innerJoin(order.customer, customer).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .leftJoin(order.attributes, attribute).fetchJoin()
                .innerJoin(orderItem.item, item).fetchJoin()
                .fetch();
    }

}
