    package com.example.repairproductprogram.model;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.sql.Date;


    @Entity
    @Table(name = "detail") // 제품 모든 상세정보 DB 테이블
    public class Detail {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;    // 고유 번호

        @Column(name = "rd")
        private boolean rd; // r: 재생품 or d: 요수리품  여부    (재생품 먼저 만들고 요수리품 추가할 예정)

        @Column(name = "date")
        private Date date;  // 완료일자

        @Column(name = "serial_num")
        private String serialNum;   // 시리얼 번호

        @Column(name = "file_url1")
        private String fileUrl1;    // 첨부파일 url

        @Column(name = "file_url2")
        private String fileUrl2;    // 첨부파일 url

        @ManyToOne
        @JoinColumn(name = "product_num", nullable = false)
        private ProductList productList;    //고유 참조 - 자재 번호 (productList 참조)

        @ManyToOne
        @JoinColumn(name = "worker_num", nullable = false)  // worker 참조
        private Member worker;  //작업자   //고유 참조 - 사원 번호 (member 참조)

        @ManyToOne
        @JoinColumn(name = "manager_num", nullable = false)  // manager 참조
        private Member manager; //관리자

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

