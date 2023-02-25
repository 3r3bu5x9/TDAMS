package com.example.tdams.controller;

import com.example.tdams.model.Role;
import com.example.tdams.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleController {
    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping("/all")
    public List<Role> showAllRoles(){
        return roleService.showAllRoles();
    }
    @PostMapping("/add")
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }
}
