package com.example.tdams.controller;

import com.example.tdams.model.*;
import com.example.tdams.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    UserService userService;
    RoleService roleService;
    AddressService addressService;
    CustomerService customerService;
    VendorService vendorService;
    DeliveryPersonnelService deliveryPersonnelService;

    public UserController(UserService userService, RoleService roleService, AddressService addressService, CustomerService customerService, VendorService vendorService, DeliveryPersonnelService deliveryPersonnelService) {
        this.userService = userService;
        this.roleService = roleService;
        this.addressService = addressService;
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.deliveryPersonnelService = deliveryPersonnelService;
    }

    @GetMapping("/all")
    public List<UserC> showAllUsers(){
        return userService.showAllUsers();
    }
    @PostMapping("/add/role/{role_id}")
    public UserC addUserWithRole(@RequestBody UserC userC, @PathVariable Long role_id)
    {
        Role role = roleService.findRoleById(role_id);
        userC.assignRole(role);
        role.assignUser(userC);
        switch (role.getName()) {
            case "CUSTOMER" -> {
                Customer customer = new Customer();
                customer.setBalance(0.0);
                customer.toUser(userC);
                customerService.addCustomer(customer);
            }
            case "VENDOR" -> {
                Vendor vendor = new Vendor();
                vendor.setBalance(0.0);
                vendor.toUser(userC);
                vendorService.addVendor(vendor);
            }
            case "DELIVERY_PERSONNEL" -> {
                DeliveryPersonnel deliveryPersonnel = new DeliveryPersonnel();
                deliveryPersonnel.setBalance(0.0);
                deliveryPersonnel.toUser(userC);
                deliveryPersonnelService.addDeliveryPersonnel(deliveryPersonnel);
            }
            default -> {
                System.out.println("Admin User!!!");
            }
        }
        return userService.saveUser(userC);
    }
    @PostMapping("/update/{user_id}")
    public UserC updateUserInfo(@PathVariable Long user_id, @RequestBody UserC newUser){
        return userService.updateUser(user_id,newUser);
    }
}
