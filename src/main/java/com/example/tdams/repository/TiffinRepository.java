package com.example.tdams.repository;

import com.example.tdams.model.Tiffin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiffinRepository extends JpaRepository<Tiffin, Long> {
}
