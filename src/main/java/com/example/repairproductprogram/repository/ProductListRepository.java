package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {

    @Query("SELECT p FROM ProductList p WHERE p.productNum = :productNum")
    List<ProductList> findByProductNum(@Param("productNum") Long productNum);

    @Query("SELECT p FROM ProductList p WHERE p.productName LIKE %:productName%")
    List<ProductList> findByProductName(@Param("productName") String productName);

    @Query("SELECT COUNT(p) > 0 FROM ProductList p WHERE p.productNum = :productNum")
    boolean existsByProductNum(@Param("productNum") Long productNum);
}
