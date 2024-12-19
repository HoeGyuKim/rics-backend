package com.example.repairproductprogram.model;

import com.example.repairproductprogram.model.Detail;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "Approval")
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "approval_status")
    private int approvalStatus; //1.상신 2.삭제신청 3.승인 4.반려

    @ManyToOne
    @JoinColumn(name = "worker_num", nullable = false)
    private Member worker;

    @Column(name = "sumbit_date")
    private LocalDateTime sumbitTime;

    @ManyToOne
    @JoinColumn(name = "manager_num", nullable = false)
    private Member manager;

    @Column(name = "approval_date")
    private LocalDateTime approvalTime;


    public LocalDateTime getSumbitTime() {
        return sumbitTime;
    }

    public void setSumbitTime(LocalDateTime datetime) {
        this.sumbitTime = datetime;
    }

    public LocalDateTime getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(LocalDateTime datetime) {
        this.approvalTime = datetime;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getWorker() {
        return worker;
    }

    public void setWorker(Member worker) {
        this.worker = worker;
    }

    public Member getManager() {
        return manager;
    }

    public void setManager(Member manager) {
        this.manager = manager;
    }
}
