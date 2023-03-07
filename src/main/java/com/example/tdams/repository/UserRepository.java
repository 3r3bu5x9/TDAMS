package com.example.tdams.repository;

import com.example.tdams.model.UserC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends JpaRepository<UserC,Long> {
    UserC findByUsername(String username);
}
