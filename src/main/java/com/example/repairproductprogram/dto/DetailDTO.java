package com.example.repairproductprogram.dto;

import java.sql.Date;

public class DetailDTO {
    private Long id;
    private Long productNum;
    private String productName;
    private Date date;
    private String serialNum;
    private String worker;  // 사원의 이름
    private String manager; // 관리자의 이름
    private String deprtmentName;
    private String url1;
    private String url2;

    // 기본 생성자
    public DetailDTO() {}

    // 모든 필드를 포함하는 생성자
    public DetailDTO(Long id, Long productNum, String productName, Date date, String serialNum, String worker, String manager, String departmentName, String url1, String url2) {
        this.id = id;
        this.productNum = productNum;
        this.productName = productName;
        this.date = date;
        this.serialNum = serialNum;
        this.worker = worker;
        this.manager = manager;
        this.deprtmentName = departmentName;
        this.url1 = url1;
        this.url2 = url2;

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return deprtmentName;
    }

    public void setDeaprtmentName(String deaprtmentName) {
        this.deprtmentName = deaprtmentName;
    }
    public String getUrl1() {
        return url1;
    }
    public void setUrl1(String fileUrl1) {
        this.url1 = fileUrl1;
    }
    public String getUrl2() {
        return url2;
    }
    public void setUrl2(String fileUrl2) {
        this.url2 = fileUrl2;
    }
}
