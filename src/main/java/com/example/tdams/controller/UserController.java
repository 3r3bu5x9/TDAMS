package com.example.tdams.controller;

import com.example.tdams.model.*;
import com.example.tdams.service.*;
import com.example.tdams.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {
    UserService userService;
    RoleService roleService;
    AddressService addressService;
    CustomerService customerService;
    VendorService vendorService;
    DeliveryPersonnelService deliveryPersonnelService;
    PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, AddressService addressService, CustomerService customerService, VendorService vendorService, DeliveryPersonnelService deliveryPersonnelService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.addressService = addressService;
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.deliveryPersonnelService = deliveryPersonnelService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/all")
    public List<UserC> showAllUsers() {
        return userService.showAllUsers();
    }

    @GetMapping("/{user_id}")
    public Object getRoleBasedUser(@PathVariable Long user_id) {
        UserC userC = userService.findUserById(user_id);
        switch (userC.getRole().getName()) {
            case "ROLE_ADMIN": {
                return new LoggedUser(0L, user_id, userC.getRole().getName());
            }
            case "ROLE_CUSTOMER": {
                return new LoggedUser(userC.getCustomer().getCid(), user_id, userC.getRole().getName());
            }
            case "ROLE_VENDOR": {
                return new LoggedUser(userC.getVendor().getVid(), user_id, userC.getRole().getName());
            }
            case "ROLE_DELIVERY_PERSONNEL": {
                return new LoggedUser(userC.getDeliveryPersonnel().getDpid(), user_id, userC.getRole().getName());
            }
        }
        return null;
    }

    @PostMapping("/add/role/{role_id}")
    public UserC addUserWithRole(@RequestBody UserC userC, @PathVariable Long role_id) {
        Role role = roleService.findRoleById(role_id);
        userC.assignRole(role);
        role.assignUser(userC);
        switch (role.getName()) {
            case "ROLE_CUSTOMER": {
                Customer customer = new Customer();
                customer.setBalance(0.0);
                customer.toUser(userC);
                customerService.addCustomer(customer);
            }
            case "ROLE_VENDOR": {
                Vendor vendor = new Vendor();
                vendor.setBalance(0.0);
                vendor.toUser(userC);
                vendorService.addVendor(vendor);
            }
            case "ROLE_DELIVERY_PERSONNEL": {
                DeliveryPersonnel deliveryPersonnel = new DeliveryPersonnel();
                deliveryPersonnel.setBalance(0.0);
                deliveryPersonnel.toUser(userC);
                deliveryPersonnelService.addDeliveryPersonnel(deliveryPersonnel);
            }
        }
        return userService.saveUser(userC);
    }

    @PostMapping("/update/{user_id}")
    public UserC updateUserInfo(@PathVariable Long user_id, @RequestBody UserC newUser) {
        return userService.updateUser(user_id, newUser);
    }
}
