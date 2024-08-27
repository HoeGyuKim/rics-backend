package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.Member;
import com.example.repairproductprogram.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByProductListProductNumAndRdTrue(Long productNum);

    List<Detail> findByProductListProductNumAndWorker(Long productNum, Member worker);

    List<Detail> findByProductListProductNumAndSerialNum(Long productNum, String serialNum);
}

