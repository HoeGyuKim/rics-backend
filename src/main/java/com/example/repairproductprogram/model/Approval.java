package com.example.repairproductprogram.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Approval")
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approval_status")
    private int approvalStatus; // 1.상신 2.1차검토완료 3.승인 4.반려 5.삭제신청 6. 삭제1차검토완료

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_num", nullable = false)
    private Member worker;

    @Column(name = "sumbit_time")
    private LocalDateTime submitTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "middle_manager_num", nullable = false)
    private Member middleManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_manager_num", nullable = false)
    private Member lastManager;

    @Column(name = "approval_date")
    private LocalDateTime approvalTime;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Member getWorker() {
        return worker;
    }

    public void setWorker(Member worker) {
        this.worker = worker;
    }

    public Member getMiddleManager() {
        return middleManager;
    }

    public void setMiddleManager(Member middleManager) {
        this.middleManager = middleManager;
    }

    public Member getLastManager() {
        return lastManager;
    }

    public void setLastManager(Member lastManager) {
        this.lastManager = lastManager;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public LocalDateTime getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(LocalDateTime approvalTime) {
        this.approvalTime = approvalTime;
    }

    // JSON serialization/deserialization helpers
    @JsonProperty("workerNum")
    public void setWorkerByNum(Long workerNum) {
        this.worker = new Member(workerNum);
    }

    @JsonProperty("middleManagerNum")
    public void setMiddleManagerByNum(Long middleManagerNum) {
        this.middleManager = new Member(middleManagerNum);
    }

    @JsonProperty("lastManagerNum")
    public void setLastManagerByNum(Long lastManagerNum) {
        this.lastManager = new Member(lastManagerNum);
    }
}
