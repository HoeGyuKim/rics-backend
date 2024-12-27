package com.example.repairproductprogram.dto;

import java.sql.Date;

public class DetailRequestDTO {
    private Date date;
    private Long productNum;
    private Long workerNum;
    private Long middleManagerNum; // 중간 관리자 번호
    private Long lastManagerNum;   // 최종 관리자 번호
    private int approvalStatus;
    private String serialNum;
    private String memo;

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Long getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(Long workerNum) {
        this.workerNum = workerNum;
    }

    public Long getMiddleManagerNum() {
        return middleManagerNum;
    }

    public void setMiddleManagerNum(Long middleManagerNum) {
        this.middleManagerNum = middleManagerNum;
    }

    public Long getLastManagerNum() {
        return lastManagerNum;
    }

    public void setLastManagerNum(Long lastManagerNum) {
        this.lastManagerNum = lastManagerNum;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
