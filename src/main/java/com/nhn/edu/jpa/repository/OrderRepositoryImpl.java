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

import javax.persistence.NoResultException;
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

        // TODO #1 : pagination query
        JPQLQuery<Long> query = from(order).select(order.orderId);
        JPQLQuery<Long> pagedQuery = getQuerydsl().applyPagination(pageable, query);

        long totalCount = 0L;
        try {
            totalCount = pagedQuery.fetchCount();
        } catch (NoResultException ex) {
            // ignore
        }

        List<Long> ids = pagedQuery.fetch();

        // TODO #2 : fetch join
        List<Order> list = from(order)
                .innerJoin(order.customer, customer).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .innerJoin(orderItem.item, item).fetchJoin()
                .where(order.orderId.in(ids))
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

}
