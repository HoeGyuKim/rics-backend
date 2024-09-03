package com.example.repairproductprogram.dto;

import java.sql.Date;

public class DetailDTO {
    private Long productNum;
    private String productName;
    private Date date;
    private String serialNum;
    private String worker;  // 사원의 이름
    private String manager; // 관리자의 이름
    private String deaprtmentName;

    // 기본 생성자
    public DetailDTO() {}

    // 모든 필드를 포함하는 생성자
    public DetailDTO(Long id, Long productNum, String productName, Date date, String serialNum, String worker, String manager, String DepartmentName) {
        this.productNum = productNum;
        this.productName = productName;
        this.date = date;
        this.serialNum = serialNum;
        this.worker = worker;
        this.manager = manager;
        this.deaprtmentName = DepartmentName;
    }

    // Getters and Setters
    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
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

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    public String getDeaprtmentName() {
        return deaprtmentName;
    }

    public void setDeaprtmentName(String deaprtmentName) {
        this.deaprtmentName = deaprtmentName;
    }
}
