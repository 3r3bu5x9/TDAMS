package com.example.tdams.service;

import com.example.tdams.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> showAllOrders();
    Order addOrder(Order order);
    Order findOrderById(Long oid);
    Order setDeliveryStatus(Long oid,Integer status);
    Order setAcceptStatus(Long oid, Integer status);
    Order setIsPickedUpStatus(Long oid,Integer status);
    void deleteOrder(Long oid);
}
