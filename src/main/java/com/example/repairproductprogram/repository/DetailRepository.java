package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.Member;
import com.example.repairproductprogram.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    // 기존 메서드 유지
    List<Detail> findByProductListProductNum(Long productNum);

    // Approval을 통해 Worker로 조회
    @Query("SELECT d FROM Detail d WHERE d.approval.worker = :worker AND d.productList.productNum = :productNum")
    List<Detail> findByProductListProductNumAndWorker(@Param("productNum") Long productNum, @Param("worker") Member worker);

    // Approval을 통해 SerialNum으로 조회
    @Query("SELECT d FROM Detail d WHERE d.serialNum = :serialNum AND d.productList.productNum = :productNum")
    List<Detail> findByProductListProductNumAndSerialNum(@Param("productNum") Long productNum, @Param("serialNum") String serialNum);
}
