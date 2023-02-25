package com.example.tdams.controller;

import com.example.tdams.model.Customer;
import com.example.tdams.model.DeliveryPersonnel;
import com.example.tdams.model.Order;
import com.example.tdams.service.CustomerService;
import com.example.tdams.service.DeliveryPersonnelService;
import com.example.tdams.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {
    OrderService orderService;
    CustomerService customerService;
    DeliveryPersonnelService deliveryPersonnelService;

    public OrderController(OrderService orderService, CustomerService customerService, DeliveryPersonnelService deliveryPersonnelService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.deliveryPersonnelService = deliveryPersonnelService;
    }
    @GetMapping("/all")
    public List<Order> showAllOrder(){
        return orderService.showAllOrders();
    }
    @PostMapping("/add")
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }
    @GetMapping("/{order_id}")
    public Order findOrderById(@PathVariable Long order_id){
        return orderService.findOrderById(order_id);
    }
    @GetMapping("/checkstatus/{order_id}")
    public Integer checkDeliveryStatus(@PathVariable Long order_id){
        return orderService.checkIfDelivered(order_id);
    }
    @PostMapping("/setstatus/{order_id}")
    public Order setDeliveryStatus(@PathVariable Long order_id, @RequestBody Integer status){
        return orderService.setDeliveryStatus(order_id,status);
    }
    @PutMapping("/{order_id}/deliveryp/{del_id}/cust/{cust_id}")
    public Order assignOrderToDeliveryPersonnel(@PathVariable Long order_id, @PathVariable Long del_id, @PathVariable Long cust_id){
        Order order = orderService.findOrderById(order_id);
        Customer customer = customerService.findCustomerById(cust_id);
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelService.findDeliveryPersonnelById(del_id);
        order.assignDeliveryPersonnel(deliveryPersonnel);
        order.assignCustomer(customer);
        deliveryPersonnel.populateOrder(order);
        customer.assignOrder(order);
        return orderService.addOrder(order);
    }
}
