package com.example.repairproductprogram.controller;

import com.example.repairproductprogram.dto.DetailDTO;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.Member;
import com.example.repairproductprogram.repository.DetailRepository;
import com.example.repairproductprogram.repository.UserRepository;
import com.example.repairproductprogram.service.DetailService;
import com.example.repairproductprogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reconditioned")
public class DetailController {

    private final DetailService detailService;
    private final DetailRepository detailRepository;
    private final UserService userService;

    @Autowired
    public DetailController(DetailService detailService, DetailRepository detailRepository, UserRepository userRepository, UserService userService) {
        this.detailService = detailService;
        this.detailRepository = detailRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDTO> getDetail(@PathVariable Long id) {
        Detail detail = detailService.getDetailById(id);

        if (detail != null) {
            DetailDTO detailWithFileDTO = detailService.toDetailWithFileDTO(detail);
            return ResponseEntity.ok(detailWithFileDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<DetailDTO>> getDetailsByProductNum(@RequestParam Long productNum) {
        List<DetailDTO> detailDTOs = detailService.getDetailsByProductNumAndRdTrue(productNum);
        List<DetailDTO> top10Details = detailDTOs.stream()
                .limit(10)
                .collect(Collectors.toList());
        return ResponseEntity.ok(top10Details);
    }
    @GetMapping("/detailsByWorker")
    public ResponseEntity <List<DetailDTO>>getDetailsByProductNumAndDate(@RequestParam Long productNum, @RequestParam String workerName){
        Member worker = userService.findByName(workerName);
        if (worker == null) {
            return ResponseEntity.badRequest().body(null); // worker가 없을 경우 처리
        }

        List<DetailDTO> detailDTOs = detailRepository.findByProductListProductNumAndWorker(productNum, worker).stream()
                .map(detailService::toDetailListDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(detailDTOs);

    }
    @GetMapping("/detailsBySerialNum")
    public ResponseEntity<List<DetailDTO>> getDetailsByProductNumAndSerialNum(
            @RequestParam Long productNum, @RequestParam String serialNum) {
        List<DetailDTO> detailDTOs = detailRepository.findByProductListProductNumAndSerialNum(productNum, serialNum)
                .stream()
                .map(detailService::toDetailListDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(detailDTOs);
    }


}
