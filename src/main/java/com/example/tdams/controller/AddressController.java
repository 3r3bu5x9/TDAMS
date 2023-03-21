package com.example.tdams.controller;

import com.example.tdams.model.Address;
import com.example.tdams.model.UserC;
import com.example.tdams.service.AddressService;
import com.example.tdams.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RequestMapping("/api/address")
@RestController
public class AddressController {
    AddressService addressService;
    UserService userService;

    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<Address> showAllAddress(){
        return addressService.showAllAddress();
    }
    @PostMapping("/add/user/{user_id}")
    public Address addAddress(@RequestBody Address address, @PathVariable Long user_id)
    {
        UserC userC = userService.findUserById(user_id);
        userC.assignAddress(address);
        address.assignUser(userC);
        return addressService.addAddress(address);

    }
    @PostMapping ("/update/{address_id}")
    public Address updateAddress(@PathVariable Long address_id, @RequestBody Address newAddress){
        return addressService.updateAddress(address_id,newAddress);
    }
}
