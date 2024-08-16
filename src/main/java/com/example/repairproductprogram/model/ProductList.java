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
    private Integer product_num;

    @Column(name = "product_name")
    private String product_name;

    // Getter와 Setter 메서드들
    public Integer getProductNum() {
        return product_num;
    }

    public void setProductNum(Integer product_num) {
        this.product_num = product_num;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }
}
