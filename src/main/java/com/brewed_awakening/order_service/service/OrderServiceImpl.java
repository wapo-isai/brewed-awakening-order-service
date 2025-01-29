package com.brewed_awakening.order_service.service;

import com.brewed_awakening.order_service.data.OrderEntity;
import com.brewed_awakening.order_service.data.OrderRepository;
import com.brewed_awakening.order_service.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    Environment environment;

    @Autowired
    public OrderServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public List<Order> getOrders(String userId) {

        List<OrderEntity> orderEntities = (List<OrderEntity>) orderRepository.findAllByUserId(userId);

        if (orderEntities == null || orderEntities.isEmpty()) {
            return new ArrayList<>();
        }

        List<Order> orders = new ArrayList<>();

        orderEntities.forEach(e -> {
            Order obtainedOrder = new Order(e.getUserId(), e.getOrderNumber(), e.getProductIds(), e.getTotalPrice());

            orders.add(obtainedOrder);
        });



        return orders;
    }

    @Override
    public Order createOrder(Order order) {
        order.setOrderNumber(UUID.randomUUID().toString());

        OrderEntity orderEntity = new OrderEntity(
                order.getUserId(),
                order.getOrderNumber(),
                order.getTotalPrice(),
                order.getProductIds()
        );

        OrderEntity storedOrderEntity = orderRepository.save(orderEntity);

        Order returnValue = new Order(
                storedOrderEntity.getUserId(),
                storedOrderEntity.getOrderNumber(),
                storedOrderEntity.getProductIds(),
                storedOrderEntity.getTotalPrice()
        );

        return returnValue;
    }
}
