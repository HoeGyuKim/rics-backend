    package com.example.repairproductprogram.model;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;
    import org.apache.catalina.Manager;

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
        public Integer getProductNum() { return (productList != null) ? productList.getProductNum() : null; }
        public String getProductName() { return (productList != null) ? productList.getProductName() : ""; }
        public void setProductList(ProductList productList) { this.productList = productList; }
    }

