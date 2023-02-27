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
        oldUserC.setDob(newUserC.getDob());
        oldUserC.setPasswd(newUserC.getPasswd());
        return userRepository.save(oldUserC);
    }

    @Override
    public UserC deleteUserById(Long uid) {
        UserC userC = userRepository.findById(uid).get();
        userRepository.deleteById(uid);
        return userC;
    }

    @Override
    public UserC setSuspensionStatus(Long uid, Integer status) {
        UserC userC = userRepository.findById(uid).get();
        userC.setIsSuspended(((status == 1) ? Boolean.TRUE:Boolean.FALSE));
        return userRepository.save(userC);
    }
}
