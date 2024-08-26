package com.example.repairproductprogram.controller;

import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.service.DetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DetailUpLoadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.access-url}")
    private String accessUrl;

    private final DetailService detailService;

    @Autowired
    public DetailUpLoadController(DetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("파일이 없습니다.");
            }

            Path uploadDirPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            if (!Files.exists(uploadDirPath)) {
                Files.createDirectories(uploadDirPath);
            }

            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadDirPath.resolve(uniqueFileName);
            Files.write(filePath, file.getBytes());

            String fileUrl = accessUrl + "/" + uniqueFileName;

            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패: " + e.getMessage());
        }
    }

    @PostMapping("/reconditioned/upload")
    public ResponseEntity<String> createDetailWithFiles(
            @RequestParam("detailData") String detailDataJson,
            @RequestParam(value = "file1", required = false) MultipartFile file1,
            @RequestParam(value = "file2", required = false) MultipartFile file2) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Detail detail = objectMapper.readValue(detailDataJson, Detail.class);

            if (file1 != null && !file1.isEmpty()) {
                String fileUrl1 = saveFile(file1);
                detail.setFileUrl1(fileUrl1);
            }

            if (file2 != null && !file2.isEmpty()) {
                String fileUrl2 = saveFile(file2);
                detail.setFileUrl2(fileUrl2);
            }

            // 데이터를 데이터베이스에 저장
            detailService.saveDetail(detail);

            return ResponseEntity.ok("데이터가 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 저장 실패: " + e.getMessage());
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("파일이 없습니다.");
        }

        Path uploadDirPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        if (!Files.exists(uploadDirPath)) {
            Files.createDirectories(uploadDirPath);
        }

        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadDirPath.resolve(uniqueFileName);
        Files.write(filePath, file.getBytes());

        return accessUrl + "/" + uniqueFileName;
    }
}
