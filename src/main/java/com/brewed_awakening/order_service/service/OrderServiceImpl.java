package com.brewed_awakening.order_service.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import com.brewed_awakening.order_service.data.OrderEntity;
import com.brewed_awakening.order_service.data.OrderRepository;
import com.brewed_awakening.order_service.dto.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getOrders(String userId) {

        List<OrderEntity> orderEntities = (List<OrderEntity>) orderRepository.findAllByUserId(userId);

        if (orderEntities == null || orderEntities.isEmpty()) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<List<Order>>() {}.getType();

        return new ModelMapper().map(orderEntities, listType);
    }

    @Override
    public Order createOrder(Order order) {
        order.setOrderNumber(UUID.randomUUID().toString());

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);

        OrderEntity storedOrderEntity = orderRepository.save(orderEntity);

        Order returnValue = new Order();

        BeanUtils.copyProperties(storedOrderEntity, returnValue);

        return returnValue;
    }
}
