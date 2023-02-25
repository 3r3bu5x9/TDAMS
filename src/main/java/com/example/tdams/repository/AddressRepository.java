package com.example.tdams.repository;

import com.example.tdams.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AddressRepository extends JpaRepository<Address, Long> {
}
