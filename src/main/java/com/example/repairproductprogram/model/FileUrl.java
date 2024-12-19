package com.example.repairproductprogram.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "file_url")
public class FileUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url1")
    private String Url1;

    @Column(name = "url2")
    private String Url2;

    public String getUrl1() {
        return Url1;
    }
    public void setUrl1(String url1) {
        Url1 = url1;
    }
    public String getUrl2() {
        return Url2;
    }
    public void setUrl2(String url2) {
        Url2 = url2;
    }
}
