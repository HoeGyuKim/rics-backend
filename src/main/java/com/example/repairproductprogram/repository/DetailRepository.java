package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    // productList의 productNum을 기준으로 검색
    @Query(value = "SELECT * FROM detail d WHERE d.product_num = :productNum", nativeQuery = true)
    List<Detail> findDetailsByProductNum(@Param("productNum") Integer productNum);

}
