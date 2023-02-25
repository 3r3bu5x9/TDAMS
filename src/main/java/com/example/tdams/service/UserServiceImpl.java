package com.example.tdams.service;

import com.example.tdams.model.UserC;
import com.example.tdams.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserC saveUser(UserC userC) {
        return userRepository.save(userC);
    }

    @Override
    public List<UserC> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserC findUserById(Long uid) {
        return userRepository.findById(uid).get();
    }

    @Override
    public UserC updateUser(Long uid, UserC newUserC) {
        UserC oldUserC = userRepository.findById(uid).get();
        oldUserC.setFirstName(newUserC.getFirstName());
        oldUserC.setLastName(newUserC.getLastName());
        oldUserC.setUname(newUserC.getUname());
        oldUserC.setMob(newUserC.getMob());
        oldUserC.setPasswd(newUserC.getPasswd());
        return userRepository.save(oldUserC);
    }

    @Override
    public UserC deleteUserById(Long uid) {
        return null;
    }
}
