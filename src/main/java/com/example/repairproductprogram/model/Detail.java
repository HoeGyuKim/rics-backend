package com.example.repairproductprogram.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.sql.Date;

@Entity
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "serial_num")
    @JsonProperty("serialNum")
    private String serialNum;

    @Column(name = "memo")
    private String memo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "approval_id", nullable = true)
    private Approval approval;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_url_id", nullable = false)
    private FileUrl fileUrl;

    @ManyToOne
    @JoinColumn(name = "product_num", nullable = false)
    @JsonProperty("productList")
    private ProductList productList;

    // Getter와 Setter 메서드들
    public Long getId() { return id; }
    public String getFormattedId() {
        return String.format("%06d", id);
    }
    public void setId(Long id) { this.id = id; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getSerialNum() { return serialNum; }
    public void setSerialNum(String serialNum) { this.serialNum = serialNum; }
    public FileUrl getFileUrl() { return fileUrl; }
    public void setFileUrl(FileUrl fileUrl) { this.fileUrl = fileUrl; }
    public ProductList getProductList() { return productList; }
    @JsonProperty("productNum")
    public void setProductNum(Long productNum) {
        this.productList = new ProductList(productNum);
    }
    @JsonProperty("productName")
    public void setProductName(Long productNum) {
        this.productList = new ProductList(productNum);
    }
    @Transient // DB에 저장하지 않기 위해 Transient로 설정
    @JsonProperty("workerNum")
    private Long workerNum;

    @Transient
    @JsonProperty("managerNum")
    private Long managerNum;
    @ManyToOne
    @JoinColumn(name = "worker_num", nullable = false)
    private Member worker;

    @ManyToOne
    @JoinColumn(name = "manager_num", nullable = false)
    private Member manager;
    public Long getProductNum() {
        return (productList != null) ? productList.getProductNum() : null;
    }
    public String getProductName() {
        return (productList != null) ? productList.getProductName() : "";
    }
    public Member getWorker() { return worker; }
    public void setWorker(Member worker) { this.worker = worker; }

    public Member getManager() { return manager; }
    public void setManager(Member manager) { this.manager = manager; }

    // workerNum과 managerNum을 받아와서 Member 객체를 설정하는 메서드 추가
    @JsonProperty("workerNum")
    public void setWorkerNum(Long workerNum) {
        this.workerNum = workerNum;
        this.worker = new Member();
        this.worker.setEmployeeNum(workerNum);
    }

    public Long getWorkerNum() {
        return workerNum;
    }

    @JsonProperty("managerNum")
    public void setManagerNum(Long managerNum) {
        this.managerNum = managerNum;
        this.manager = new Member();
        this.manager.setEmployeeNum(managerNum);
    }

    public Long getManagerNum() {
        return managerNum;
    }
    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
    public Approval getApproval() { return approval; }
    @JsonProperty("approvalStatus")
    public void setApprovalStatus(int approvalStatus) {
        if (this.approval == null) {
            this.approval = new Approval();
        }
        this.approval.setApprovalStatus(approvalStatus);
    }

    public int getApprovalStatus() {
        return (approval != null) ? approval.getApprovalStatus() : 0; // 기본값 0 반환
    }


    public void setApproval(Approval approval) {
        this.approval = approval;
    }
}



