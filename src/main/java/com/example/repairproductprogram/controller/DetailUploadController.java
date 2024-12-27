package com.example.repairproductprogram.controller;

import com.example.repairproductprogram.dto.DetailRequestDTO;
import com.example.repairproductprogram.model.Approval;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.FileUrl;
import com.example.repairproductprogram.service.DetailService;
import com.example.repairproductprogram.repository.UserRepository;
import com.example.repairproductprogram.repository.ProductListRepository;
import com.example.repairproductprogram.service.DetailUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reconditioned")
public class DetailUploadController {

    private final DetailService detailService;
    private final DetailUploadService detailUploadService;
    private final UserRepository userRepository;
    private final ProductListRepository productListRepository;

    @Autowired
    public DetailUploadController(DetailService detailService, DetailUploadService detailUploadService, UserRepository userRepository, ProductListRepository productListRepository) {
        this.detailService = detailService;
        this.detailUploadService = detailUploadService;
        this.userRepository = userRepository;
        this.productListRepository = productListRepository;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createDetailWithApproval(
            @RequestParam("detailData") String detailDataJson,
            @RequestParam(value = "file1", required = false) MultipartFile file1,
            @RequestParam(value = "file2", required = false) MultipartFile file2) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DetailRequestDTO detailRequest = objectMapper.readValue(detailDataJson, DetailRequestDTO.class);

            // 파일 저장 경로 설정
            String uploadPath = "C:/Users/kim/IntellijProject/rics-backend/uploads/";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // 디렉토리가 없으면 생성
            }

            // 파일 저장 처리
            FileUrl fileUrl = new FileUrl();
            if (file1 != null) {
                String safeFileName1 = file1.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
                String filePath1 = uploadPath + safeFileName1;
                file1.transferTo(new File(filePath1));
                fileUrl.setUrl1(filePath1);
            }
            if (file2 != null) {
                String safeFileName2 = file2.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
                String filePath2 = uploadPath + safeFileName2;
                file2.transferTo(new File(filePath2));
                fileUrl.setUrl2(filePath2);
            }

            // 기존 Detail 및 Approval 처리 로직
            Detail detail = new Detail();
            detail.setDate(detailRequest.getDate());
            detail.setProductNum(detailRequest.getProductNum());
            detail.setApprovalStatus(detailRequest.getApprovalStatus());
            detail.setFileUrl(fileUrl); // FileUrl 연결
            detail.setSerialNum(detailRequest.getSerialNum());
            detail.setMemo(detailRequest.getMemo());

            Approval approval = new Approval();
            approval.setApprovalStatus(detailRequest.getApprovalStatus());
            approval.setSubmitTime(LocalDateTime.now());
            approval.setWorkerByNum(detailRequest.getWorkerNum());
            approval.setMiddleManagerByNum(detailRequest.getMiddleManagerNum());
            approval.setLastManagerByNum(detailRequest.getLastManagerNum());
            detail.setApproval(approval);

            detailUploadService.saveDetailWithApproval(detail, detailRequest.getApprovalStatus(),
                    detailRequest.getMiddleManagerNum(), detailRequest.getWorkerNum(), detailRequest.getLastManagerNum());

            return ResponseEntity.ok("데이터 저장 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 저장 실패: " + e.getMessage());
        }
    }


}
