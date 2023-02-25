package com.example.tdams.service;

import com.example.tdams.model.Order;
import com.example.tdams.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long oid) {
        return orderRepository.findById(oid).get();
    }

    @Override
    public Integer checkIfDelivered(Long oid) {
        return orderRepository.findById(oid).get().getIsDelivered() ? 1 : 0;
    }

    @Override
    public Order setDeliveryStatus(Long oid,Integer status) {
        Order order = orderRepository.findById(oid).get();
        order.setIsDelivered((status == 1) ? Boolean.TRUE:Boolean.FALSE);
        return orderRepository.save(order);
    }
}