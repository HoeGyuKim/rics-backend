package com.example.repairproductprogram.dto;

import java.sql.Date;

public class DetailDTO {
    private Long id;
    private Long productNum;
    private String productName;
    private Date date;
    private String serialNum;
    private String workerName;  // 사원의 이름
    private String middleManagerName; // 관리자의 이름
    private String lastManagerName;
    private String departmentName;
    private String url1;
    private String url2;
    private String memo;
    private int approvalStatus;

    // 기본 생성자
    public DetailDTO() {}

    // 모든 필드를 포함하는 생성자
    public DetailDTO(Long id, Long productNum, String productName, Date date, String serialNum, String workerName, String middleManagerName, String lastManagerName, String departmentName, String memo, int approvalStatus) {
        this.id = id;
        this.productNum = productNum;
        this.productName = productName;
        this.date = date;
        this.serialNum = serialNum;
        this.workerName = workerName;
        this.middleManagerName = middleManagerName;
        this.lastManagerName = lastManagerName;
        this.departmentName = departmentName;
        this.memo = memo;
        this.approvalStatus = approvalStatus;
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
    public String getWorkerName() {
        return workerName;
    }
    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
    public String getMiddleManagerName() {
        return middleManagerName;
    }
    public void setMiddleManagerName(String middleManagerName) {
        this.middleManagerName = middleManagerName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDeprtmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getUrl1() {
        return url1;
    }
    public void setUrl1(String fileUrl) {
        this.url1 = fileUrl;
    }
    public String getUrl2() {
        return url2;
    }
    public void setUrl2(String fileUrl2) {
        this.url2 = fileUrl2;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public int getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

}
