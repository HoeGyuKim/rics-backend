package com.example.repairproductprogram.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rd")
    private boolean rd;

    @Column(name = "date")
    private Date date;

    @Column(name = "serial_num")
    @JsonProperty("serialNum")
    private String serialNum;

    @Column(name = "file_url1")
    private String fileUrl1;

    @Column(name = "file_url2")
    private String fileUrl2;

    @ManyToOne
    @JoinColumn(name = "product_num", nullable = false)
    @JsonProperty("productList")
    private ProductList productList;

    // Worker와 Manager의 ID를 직접 받아오기 위한 필드 추가
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

    // Getter와 Setter 메서드들
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isRd() { return rd; }
    public void setRd(boolean rd) { this.rd = rd; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getSerialNum() { return serialNum; }
    public void setSerialNum(String serialNum) { this.serialNum = serialNum; }
    public String getFileUrl1() { return fileUrl1; }
    public void setFileUrl1(String fileUrl1) { this.fileUrl1 = fileUrl1; }
    public String getFileUrl2() { return fileUrl2; }
    public void setFileUrl2(String fileUrl2) { this.fileUrl2 = fileUrl2; }
    public ProductList getProductList() { return productList; }

    @JsonProperty("productNum")
    public void setProductNum(Long productNum) {
        this.productList = new ProductList(productNum);
    }
    @JsonProperty("productName")
    public void setProductName(Long productNum) {
        this.productList = new ProductList(productNum);
    }

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
}



