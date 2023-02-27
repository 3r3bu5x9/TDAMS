package com.example.tdams.controller;

import com.example.tdams.model.Customer;
import com.example.tdams.model.Tiffin;
import com.example.tdams.model.TiffinDetail;
import com.example.tdams.service.CustomerService;
import com.example.tdams.service.TiffinDetailService;
import com.example.tdams.service.TiffinService;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
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

    @GetMapping("/add/cust/{cust_id}")
    public Tiffin addTiffin(@PathVariable Long cust_id)
    {
        Tiffin tiffin = new Tiffin();
        Customer customer = customerService.findCustomerById(cust_id);
        tiffin.assignCustomer(customer);
        customer.assignTiffin(tiffin);
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
    @GetMapping("/cust/{cust_id}")
    public Tiffin getTiffinByCustId(@PathVariable Long cust_id){
        return customerService.findCustomerById(cust_id).getTiffin();
    }
    @PostMapping("/update/{cust_id}")
    public Tiffin updateTiffin(@PathVariable Long cust_id, @RequestBody Tiffin newTiffin){
        Tiffin oldTiffin = customerService.findCustomerById(cust_id).getTiffin();
        Iterator<TiffinDetail> oldI = oldTiffin.getTiffinDetails().iterator();
        Iterator<TiffinDetail> newI = newTiffin.getTiffinDetails().iterator();
        while(oldI.hasNext() && newI.hasNext()){
            oldI.next().setQty(newI.next().getQty());
        }
        return tiffinService.addTiffin(oldTiffin);
    }
    @GetMapping("/delete/cust/{cust_id}")
    public Tiffin deleteTiffin(@PathVariable Long cust_id){
        return tiffinService.deleteTiffin(customerService.findCustomerById(cust_id).getTiffin().getTid());
    }
}
