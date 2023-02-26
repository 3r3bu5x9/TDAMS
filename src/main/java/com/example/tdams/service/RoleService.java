package com.example.tdams.service;

import com.example.tdams.model.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);
    List<Role> addRoles(List<Role> roles);
    List<Role> showAllRoles();
    Role findRoleById(Long rid);
}
