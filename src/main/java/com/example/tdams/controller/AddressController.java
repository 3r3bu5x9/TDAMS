package com.example.tdams.controller;

import com.example.tdams.model.Address;
import com.example.tdams.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/address")
@RestController
public class AddressController {
    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping("/all")
    public List<Address> showAllAddress(){
        return addressService.showAllAddress();
    }
    @PostMapping("/add")
    public Address addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }
}
