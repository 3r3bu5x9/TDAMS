package com.example.tdams.repository;

import com.example.tdams.model.TiffinDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiffinDetailRepository extends JpaRepository<TiffinDetail, Long> {
}
