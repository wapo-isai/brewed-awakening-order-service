package com.brewed_awakening.order_service.service;

import com.brewed_awakening.order_service.dto.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(String userId);
    Order createOrder(Order order);
}
