package com.example.repairproductprogram.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "fileinfo")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String fileUrl;  // 파일 URL

    @Getter
    @Setter
    @Column(nullable = false)
    private String fileName;
}
