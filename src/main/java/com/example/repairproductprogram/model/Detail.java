    package com.example.repairproductprogram.model;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

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
        private String serialNum;

        @Column(name = "file_url1")
        private String fileUrl1;

        @Column(name = "file_url2")
        private String fileUrl2;

        @ManyToOne
        @JoinColumn(name = "product_num", nullable = false)
        private ProductList productList;

        @ManyToOne
        @JoinColumn(name = "worker", nullable = false)  // worker 참조
        private Member worker;

        @ManyToOne
        @JoinColumn(name = "manager", nullable = false)  // manager 참조
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

        public Integer getProductNum() { return (productList != null) ? productList.getProductNum() : null; }
        public String getProductName() { return (productList != null) ? productList.getProductName() : ""; }
        public void setProductList(ProductList productList) { this.productList = productList; }
        public Member getWorker() { return worker;}
        public void setWorker(Member worker) { this.worker = worker; }
        public Member getManager() { return manager; }
        public void setManager(Member manager) { this.manager = manager; }
    }

