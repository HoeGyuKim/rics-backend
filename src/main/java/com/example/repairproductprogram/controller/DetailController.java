package com.example.repairproductprogram.controller;

import com.example.repairproductprogram.NotFoundException.ProductListNotFoundException;
import com.example.repairproductprogram.dto.DetailDTO;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.Member;
import com.example.repairproductprogram.model.ProductList;
import com.example.repairproductprogram.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;

@RestController
@RequestMapping("/api/reconditioned")
public class DetailController {
    @Autowired
    private DetailService detailService;


    private static final String UPLOAD_DIR = "uploads/";
    @PostMapping("/upload")
    public ResponseEntity<Detail> createDetailWithFiles(
            @RequestParam("rd") boolean rd,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("serialNum") String serialNum,
            @RequestParam("productNum") Integer productNum,
            @RequestParam("workerId") Long workerId,
            @RequestParam("managerId") Long managerId,
            @RequestParam(value = "file1", required = false) MultipartFile file1,
            @RequestParam(value = "file2", required = false) MultipartFile file2) {

        try {
            // 파일 저장 처리
            String fileUrl1 = saveFile(file1);
            String fileUrl2 = saveFile(file2);

            // 엔티티 생성 및 저장
            Detail detail = new Detail();
            detail.setRd(rd);
            detail.setDate(Date.valueOf(date));
            detail.setSerialNum(serialNum);
            detail.setFileUrl1(fileUrl1);
            detail.setFileUrl2(fileUrl2);

            // ProductList 및 Member 객체를 엔티티에 직접 설정
            ProductList productList = new ProductList();
            productList.setProductNum(productNum);
            detail.setProductList(productList);

            Member worker = new Member();
            worker.setEmployeeNum(workerId);
            detail.setWorker(worker);

            Member manager = new Member();
            manager.setEmployeeNum(managerId);
            detail.setManager(manager);

            // Detail 저장
            Detail savedDetail = detailService.saveDetail(detail);

            return ResponseEntity.ok(savedDetail);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    private String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            return filePath.toString();
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }
    @GetMapping
    public List<Detail> getDetail() {
        return detailService.getAllDetail();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detail> getDetailById(@PathVariable Long id) {
        Optional<Detail> detail = detailService.getDetailById(id);
        return detail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Detail createDetail(@RequestBody Detail detail) {
        return detailService.saveDetail(detail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detail> updateDetail(@PathVariable Long id, @RequestBody Detail detail) {
        if (!detailService.getDetailById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        detail.setId(id);
        Detail updatedDetail = detailService.saveDetail(detail);
        return ResponseEntity.ok(updatedDetail);
    }
    @GetMapping("/all")
    public List<Detail> getAllDetails() {
        return detailService.getAllDetail();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetail(@PathVariable Long id) {
        try {
            detailService.deleteDetail(id);
            return ResponseEntity.noContent().build();
        } catch (ProductListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 자재 번호를 기준으로 상세 정보 조회
    @GetMapping("/details")
    public List<DetailDTO> getDetails(@RequestParam Integer productNum) {
        return detailService.getDetailsByProductNumAndRdTrue(productNum);
    }
}
