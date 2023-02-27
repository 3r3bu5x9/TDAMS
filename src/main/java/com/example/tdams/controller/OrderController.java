package com.example.tdams.controller;

import com.example.tdams.model.*;
import com.example.tdams.service.CustomerService;
import com.example.tdams.service.DeliveryPersonnelService;
import com.example.tdams.service.OrderService;
import com.example.tdams.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {
    OrderService orderService;
    CustomerService customerService;
    DeliveryPersonnelService deliveryPersonnelService;
    VendorService vendorService;

    public OrderController(OrderService orderService, CustomerService customerService, DeliveryPersonnelService deliveryPersonnelService, VendorService vendorService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.deliveryPersonnelService = deliveryPersonnelService;
        this.vendorService = vendorService;
    }

    @GetMapping("/all")
    public List<Order> showAllOrder(){
        return orderService.showAllOrders();
    }
    @GetMapping("/add/cust/{cust_id}")
    public Order addOrder(@PathVariable Long cust_id)
    {
        Order order = new Order();
        Customer customer = customerService.findCustomerById(cust_id);
        order.assignCustomer(customer);
        customer.assignOrder(order);
        order.setIsDelivered(Boolean.FALSE);
        order.setIsPickedUp(Boolean.FALSE);
        return orderService.addOrder(order);
    }
    @GetMapping("/cust/{cust_id}")
    public Order showCustOrder(@PathVariable Long cust_id){
        return customerService.findCustomerById(cust_id).getOrder();
    }
    @GetMapping("/{order_id}")
    public Order findOrderById(@PathVariable Long order_id){
        return orderService.findOrderById(order_id);
    }
    @PostMapping("/set/delstatus/{order_id}")
    public Order setDeliveryStatus(@PathVariable Long order_id, @RequestBody Integer status){
        double itempricexqty = 0.0;
        Order order = orderService.findOrderById(order_id);
        Customer customer = order.getCustomer();
        Tiffin tiffin = customer.getTiffin();
        List<TiffinDetail> tiffinDetails = tiffin.getTiffinDetails();
        orderService.setDeliveryStatus(order_id,status);
        for (TiffinDetail td : tiffinDetails) {
            itempricexqty += td.getQty()*td.getItem().getPrice();
        }
        customer.setBalance(customer.getBalance()+itempricexqty);
        customerService.addCustomer(customer);
        return orderService.addOrder(order);
    }
    @PostMapping("/set/acptstatus/{order_id}/delp/{del_id}")
    public Order setPickedUpStatus(@PathVariable Long order_id, @PathVariable Long del_id,@RequestBody Integer status){
        double itempricexqty = 0.0;
        Order order = orderService.findOrderById(order_id);
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelService.findDeliveryPersonnelById(del_id);
        deliveryPersonnel.populateOrder(order);
        order.assignDeliveryPersonnel(deliveryPersonnel);
        Customer customer = order.getCustomer();
        Tiffin tiffin = customer.getTiffin();
        List<TiffinDetail> tiffinDetails = tiffin.getTiffinDetails();
        orderService.setIsPickedUpStatus(order_id,status);
        for (TiffinDetail td : tiffinDetails) {
            Vendor vendor = td.getItem().getVendor();
            itempricexqty = td.getQty()*td.getItem().getPrice();
            vendor.setBalance(vendor.getBalance()+itempricexqty);
            vendorService.addVendor(vendor);
        }
        return orderService.setIsPickedUpStatus(order_id,status);
    }
}
