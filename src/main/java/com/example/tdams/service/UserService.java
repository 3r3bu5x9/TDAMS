package com.example.tdams.service;

import com.example.tdams.model.UserC;

import java.util.List;

public interface UserService {
    UserC saveUser(UserC userC);
    List<UserC> showAllUsers();
    UserC findUserById(Long uid);
    UserC updateUser(Long uid, UserC newUserC);
    UserC deleteUserById(Long uid);
    UserC setSuspensionStatus(Long uid, Integer status);
}
