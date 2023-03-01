package com.example.tdams.controller;

import com.example.tdams.model.Vendor;
import com.example.tdams.service.ItemService;
import com.example.tdams.service.UserService;
import com.example.tdams.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RequestMapping("/vendor")
@RestController
public class VendorController {
    VendorService vendorService;
    UserService userService;
    ItemService itemService;

    public VendorController(VendorService vendorService, UserService userService, ItemService itemService) {
        this.vendorService = vendorService;
        this.userService = userService;
        this.itemService = itemService;
    }
    @GetMapping("/all")
    public List<Vendor> showAllVendors(){
        return vendorService.showAllVendor();
    }
    @GetMapping("/bal/{vendor_id}")
    public Double getBalance(@PathVariable Long vendor_id){
        return vendorService.getBalance(vendor_id);
    }
    @GetMapping("/{vendor_id}")
    public Vendor findVendorById(@PathVariable Long vendor_id){
        return vendorService.findVendorById(vendor_id);
    }
}
