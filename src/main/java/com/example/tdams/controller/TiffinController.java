package com.example.tdams.controller;

import com.example.tdams.model.Customer;
import com.example.tdams.model.Tiffin;
import com.example.tdams.model.TiffinDetail;
import com.example.tdams.service.CustomerService;
import com.example.tdams.service.TiffinDetailService;
import com.example.tdams.service.TiffinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tiffin")
@RestController
public class TiffinController {
    TiffinService tiffinService;
    TiffinDetailService tiffinDetailService;
    CustomerService customerService;

    public TiffinController(TiffinService tiffinService, TiffinDetailService tiffinDetailService, CustomerService customerService) {
        this.tiffinService = tiffinService;
        this.tiffinDetailService = tiffinDetailService;
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public Tiffin addTiffin(@RequestBody Tiffin tiffin){
        return tiffinService.addTiffin(tiffin);
    }
    @GetMapping("all")
    public List<Tiffin> showAllTiffins(){
        return tiffinService.showAllTiffins();
    }
    @GetMapping("/{tiffin_id}")
    public Tiffin getTiffinById(@PathVariable Long tiffin_id){
        return tiffinService.findTiffinById(tiffin_id);
    }
    @PutMapping("/{tiffin_id}/tiffindetail/{tiffin_detail_id}")
    public Tiffin assignTiffinDetailToTiffin(@PathVariable Long tiffin_id, @PathVariable Long tiffin_detail_id){
        TiffinDetail tiffinDetail = tiffinDetailService.findTiffinDetailById(tiffin_detail_id);
        Tiffin tiffin = tiffinService.findTiffinById(tiffin_id);
        tiffin.addDetails(tiffinDetail);
        tiffinDetail.assignTiffin(tiffin);
        return tiffinService.addTiffin(tiffin);
    }
    @PutMapping("/{tiffin_id}/cust/{cust_id}")
    public Customer assignTiffinToCustomer(@PathVariable Long tiffin_id, @PathVariable Long cust_id){
        Tiffin tiffin = tiffinService.findTiffinById(tiffin_id);
        Customer customer = customerService.findCustomerById(cust_id);
        tiffin.assignCustomer(customer);
        customer.assignTiffin(tiffin);
        return customerService.addCustomer(customer);
    }
}
