package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.dto.DetailDTO;
import com.example.repairproductprogram.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    @Query("SELECT new com.example.repairproductprogram.dto.DetailDTO(d.id, d.productList.product_num, d.productList.product_name, d.date, d.serialNum) " +
            "FROM Detail d WHERE d.productList.product_num = :productNum AND d.rd = true")
    List<DetailDTO> findDtoByProductNumAndRdTrue(@Param("productNum") Integer productNum);

}

