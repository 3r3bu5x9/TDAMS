package com.example.tdams.controller;

import com.example.tdams.model.UserC;
import com.example.tdams.model.Vendor;
import com.example.tdams.service.UserService;
import com.example.tdams.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vendor")
@RestController
public class VendorController {
    VendorService vendorService;
    UserService userService;

    public VendorController(VendorService vendorService, UserService userService) {
        this.vendorService = vendorService;
        this.userService = userService;
    }

    @PostMapping("/vendor")
    public Vendor addVendor(@RequestBody Vendor vendor){
        return vendorService.addVendor(vendor);
    }
    @GetMapping("/all")
    public List<Vendor> showAllVendors(){
        return vendorService.showAllVendor();
    }
    @GetMapping("/bal/{vendor_id}")
    public Double getBalance(@PathVariable Long vendor_id){
        return vendorService.getBalance(vendor_id);
    }
    @PutMapping("/{vendor_id}/user/{user_id}")
    public Vendor toUser(@PathVariable Long vendor_id, @PathVariable Long user_id){
        UserC userC = userService.findUserById(user_id);
        Vendor vendor = vendorService.findVendorById(vendor_id);
        vendor.toUser(userC);
        userC.toVendor(vendor);
        return vendorService.addVendor(vendor);
    }
}
