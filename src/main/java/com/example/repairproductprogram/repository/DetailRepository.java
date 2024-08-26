package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByProductListProductNumAndRdTrue(@Param("productNum") Long productNum);

}

