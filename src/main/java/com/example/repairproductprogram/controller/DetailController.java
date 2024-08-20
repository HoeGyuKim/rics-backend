package com.example.repairproductprogram.controller;

import com.example.repairproductprogram.NotFoundException.ProductListNotFoundException;
import com.example.repairproductprogram.dto.DetailDTO;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reconditioned")
public class DetailController {
    @Autowired
    private DetailService detailService;

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
