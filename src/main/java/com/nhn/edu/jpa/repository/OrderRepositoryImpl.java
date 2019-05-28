package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.entity.Order;
import com.nhn.edu.jpa.entity.QCustomer;
import com.nhn.edu.jpa.entity.QItem;
import com.nhn.edu.jpa.entity.QOrder;
import com.nhn.edu.jpa.entity.QOrderItem;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

        return from(order)
                .innerJoin(order.customer, customer).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .innerJoin(orderItem.item, item).fetchJoin()
                .fetch();
    }

    @Override
    public Page<Order> getPagedOrderWithAssociations(Pageable pageable) {
        QOrder order = QOrder.order;
        QCustomer customer = QCustomer.customer;
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;

        JPQLQuery<Order> query = from(order)
                .innerJoin(order.customer, customer).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .innerJoin(orderItem.item, item).fetchJoin();

        List<Order> list = getQuerydsl().applyPagination(pageable, query)
                                        .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }

}
