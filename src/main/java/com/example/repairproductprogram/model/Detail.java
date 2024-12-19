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

    @ManyToOne
    @JoinColumn(name = "approval_id", nullable = false)
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
    public Long getProductNum() {
        return (productList != null) ? productList.getProductNum() : null;
    }
    public String getProductName() {
        return (productList != null) ? productList.getProductName() : "";
    }
    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
    public Approval getApproval() { return approval; }
    public void setApproval(Approval approval) { this.approval = approval; }
}



