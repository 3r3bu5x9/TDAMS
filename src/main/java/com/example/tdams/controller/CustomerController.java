package com.example.tdams.controller;

import com.example.tdams.model.Customer;
import com.example.tdams.model.UserC;
import com.example.tdams.service.CustomerService;
import com.example.tdams.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cust")
@RestController
public class CustomerController {
    CustomerService customerService;
    UserService userService;

    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }
    @GetMapping("/all")
    public List<Customer> showAllCustomers(){
        return customerService.showAllCustomers();
    }
    @GetMapping("/{cust_id}")
    public Customer findCustomerById(@PathVariable Long cust_id){
        return customerService.findCustomerById(cust_id);
    }
    @PostMapping("/bal/{cust_id}")
    public Double getBalance(@PathVariable Long cust_id, @RequestBody Double newBal){
        return customerService.updateBal(cust_id,newBal);
    }
    @PutMapping("/{cust_id}/user/{user_id}")
    public Customer toUser(@PathVariable Long cust_id, @PathVariable Long user_id){
        Customer customer = customerService.findCustomerById(cust_id);
        UserC userC = userService.findUserById(user_id);
        customer.toUser(userC);
        userC.toCustomer(customer);
        return customerService.addCustomer(customer);
    }
}
