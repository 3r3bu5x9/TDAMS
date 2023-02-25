package com.example.tdams.service;

import com.example.tdams.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    List<Customer> showAllCustomers();
    Customer findCustomerById(Long cid);
    Double updateBal(Long cid, Double newBal);

}
