package com.example.tdams.controller;

import com.example.tdams.model.Address;
import com.example.tdams.model.Role;
import com.example.tdams.model.UserC;
import com.example.tdams.service.AddressService;
import com.example.tdams.service.RoleService;
import com.example.tdams.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    UserService userService;
    RoleService roleService;
    AddressService addressService;

    public UserController(UserService userService, RoleService roleService, AddressService addressService) {
        this.userService = userService;
        this.roleService = roleService;
        this.addressService = addressService;
    }
    @GetMapping("/all")
    public List<UserC> showAllUsers(){
        return userService.showAllUsers();
    }
    @PostMapping("/add")
    public UserC saveUser(@RequestBody UserC userC){
        return userService.saveUser(userC);
    }
    @PutMapping("/{uid}/address/{aid}")
    public UserC assignAddressToUser(@PathVariable Long uid, @PathVariable Long aid){
        UserC userC = userService.findUserById(uid);
        Address address = addressService.findAddressById(aid);
        userC.assignAddress(address);
        address.assignUser(userC);
        return userService.saveUser(userC);
    }
    @PutMapping("/{uid}/role/{rid}")
    public UserC assignRoleToUser(@PathVariable Long uid, @PathVariable Long rid){
        UserC userC = userService.findUserById(uid);
        Role role = roleService.findRoleById(rid);
        userC.assignRole(role);
        role.assignUser(userC);
        return userService.saveUser(userC);
    }
}
