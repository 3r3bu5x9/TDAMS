package com.example.tdams.controller;

import com.example.tdams.model.Customer;
import com.example.tdams.model.Item;
import com.example.tdams.model.Tiffin;
import com.example.tdams.model.TiffinDetail;
import com.example.tdams.service.CustomerService;
import com.example.tdams.service.ItemService;
import com.example.tdams.service.TiffinDetailService;
import com.example.tdams.service.TiffinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RequestMapping("/tiffindetail")
@RestController
public class TiffinDetailController {
    TiffinDetailService tiffinDetailService;
    ItemService itemService;
    TiffinService tiffinService;
    CustomerService customerService;

    public TiffinDetailController(TiffinDetailService tiffinDetailService, ItemService itemService, TiffinService tiffinService, CustomerService customerService) {
        this.tiffinDetailService = tiffinDetailService;
        this.itemService = itemService;
        this.tiffinService = tiffinService;
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<TiffinDetail> showAllTiffinDetail(){
        return tiffinDetailService.showAllTiffinDetail();
    }
    @PostMapping("/add/cust/{cust_id}")
    public Tiffin addTiffinDetail(@PathVariable Long cust_id,@RequestBody TiffinDetail tiffinDetail){
        Customer customer = customerService.findCustomerById(cust_id);
        Tiffin tiffin = customer.getTiffin();
        tiffin.addDetails(tiffinDetail);
        tiffinDetail.assignTiffin(tiffin);
        customer.assignTiffin(tiffin);
        tiffin.assignCustomer(customer);
        return tiffinService.addTiffin(tiffin);
    }
//    @GetMapping("/delete/{tiffin_detail_id}/cust/{cust_id}")
//    public Tiffin deleteTiffinDetail(@PathVariable Long cust_id,@PathVariable Long tiffin_detail_id){
//        Tiffin tiffin = customerService.findCustomerById(cust_id).getTiffin();
//        tiffin.getTiffinDetails().remove(tiffinDetailService.findTiffinDetailById(tiffin_detail_id));
//        return tiffinService.addTiffin(tiffin);
//    }
}
