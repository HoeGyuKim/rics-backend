// R_Dto.java
package com.example.repairproductprogram.dto;

public class R_Dto {
    private int productNumber;
    private String productName;

    // 기본 생성자 추가
    public R_Dto() {
    }

    // 매개변수 생성자
    public R_Dto(int productNumber, String productName) {
        this.productNumber = productNumber;
        this.productName = productName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
