package com.example.tdams.service;

import com.example.tdams.model.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);
    List<Role> showAllRoles();
    Role findRoleById(Long rid);
}
