package com.nhn.edu.jpa.dto;

import java.util.List;

public interface OrderDto {
    Long getOrderId();
    CustomerDto getCustomer();
    List<OrderItemDto> getOrderItems();

    interface CustomerDto {
        String getCustomerName();
    }

    interface OrderItemDto {
        ItemDto getItem();
        Long getQuantity();
    }

    interface ItemDto {
        String getItemName();
        Long getItemPrice();
    }

}
