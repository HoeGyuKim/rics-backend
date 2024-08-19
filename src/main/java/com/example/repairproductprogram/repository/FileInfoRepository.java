package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findByFileUrl(String fileUrl);
}