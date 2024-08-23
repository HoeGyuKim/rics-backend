package com.example.repairproductprogram.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController

@RequestMapping("/api/upload")
public class DetailUpLoadController {
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "파일이 없습니다.";
            }

            // 디렉토리 확인 및 생성
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // 파일 이름 고유화
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = uploadDir.resolve(uniqueFileName);
            Files.write(path, file.getBytes());

            return path.toString(); // 저장된 파일 경로를 반환합니다.
        } catch (Exception e) {
            e.printStackTrace();
            return "파일 업로드 실패: " + e.getMessage();
        }
    }
}
