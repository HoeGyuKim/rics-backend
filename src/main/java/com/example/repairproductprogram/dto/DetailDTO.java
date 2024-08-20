package com.example.repairproductprogram.dto;

import java.sql.Date;

public class DetailDTO {
    private Long id;
    private Integer productNum;
    private String productName;
    private Date date;
    private String serialNum;

    // 기본 생성자 (필수)
    public DetailDTO() {}

    // 모든 필드를 포함하는 생성자
    public DetailDTO(Long id, Integer productNum, String productName, Date date, String serialNum) {
        this.id = id;
        this.productNum = productNum;
        this.productName = productName;
        this.date = date;
        this.serialNum = serialNum;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
}
