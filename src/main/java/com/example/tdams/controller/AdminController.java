package com.example.tdams.controller;

import com.example.tdams.model.UserC;
import com.example.tdams.service.UserService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RequestMapping("/admin/")
@RestController
public class AdminController {
    UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/suspend/user/{user_id}")
    public UserC suspendUser(@PathVariable Long user_id, @RequestBody Integer status){
        return userService.setSuspensionStatus(user_id, status);
    }
    @GetMapping("/delete/user/{user_id}")
    public UserC deleteUser(@PathVariable Long user_id){
        return userService.deleteUserById(user_id);
    }
}
