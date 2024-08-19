    package com.example.repairproductprogram.model;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.sql.Date;

    @Getter
    @Setter
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
        @JoinColumn(name = "product_num", referencedColumnName = "product_num", nullable = false)
        private ProductList productList;

        // ID에 대한 getter와 setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        // ProductList 필드의 getter 메소드
        public Integer getProductNum() {
            return (productList != null) ? productList.getProductNum() : null;
        }

        public String getProductName() {
            return (productList != null) ? productList.getProductName() : "";
        }

        public void setProductList(ProductList productList) {
            this.productList = productList;
        }
    }
