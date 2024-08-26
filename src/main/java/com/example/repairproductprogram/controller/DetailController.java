package com.example.repairproductprogram.controller;

import com.example.repairproductprogram.dto.DetailDTO;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reconditioned")
public class DetailController {

    private final DetailService detailService;

    @Autowired
    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDTO> getDetail(@PathVariable Long id) {
        Detail detail = detailService.getDetailById(id);

        if (detail != null) {
            DetailDTO detailDTO = detailService.toDetailDTO(detail);
            return ResponseEntity.ok(detailDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<DetailDTO>> getDetails(@RequestParam Long productNum) {
        List<DetailDTO> detailDTOs = detailService.getDetailsByProductNumAndRdTrue(productNum);
        return ResponseEntity.ok(detailDTOs);
    }
}
