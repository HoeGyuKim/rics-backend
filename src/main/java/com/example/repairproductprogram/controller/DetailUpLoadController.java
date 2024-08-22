package com.example.repairproductprogram.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/upload")
public class DetailUpLoadController {
    private static String UPLOAD_DIR = "C:/uploads/";

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "파일이 없습니다.";
            }

            // 파일 저장 경로 생성
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            return path.toString(); // 저장된 파일 경로를 반환합니다.
        } catch (Exception e) {
            e.printStackTrace();
            return "파일 업로드 실패: " + e.getMessage();
        }
    }
}
