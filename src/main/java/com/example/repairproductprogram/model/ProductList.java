package com.example.repairproductprogram.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productlist")
public class ProductList {
    @Id
    @Column(name = "product_num")
    private Long productNum;

    @Column(name = "product_name")
    private String productName;

    // 기본 생성자
    public ProductList() {
    }

    // productNum을 인자로 받는 생성자 추가
    public ProductList(Long productNum) {
        this.productNum = productNum;
    }

    // Getter와 Setter 메서드들
    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long product_num) {
        this.productNum = product_num;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }
}

