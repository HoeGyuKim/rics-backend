package com.example.repairproductprogram.service;

import com.example.repairproductprogram.dto.DetailDTO;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.repository.ApprovalRepository;
import com.example.repairproductprogram.repository.DetailRepository;
import com.example.repairproductprogram.repository.ProductListRepository;
import com.example.repairproductprogram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailService {
    private final DetailRepository detailRepository;
    private final ProductListRepository productListRepository;
    private final ApprovalRepository approvalRepository;
    private final UserRepository userRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository, ProductListRepository productListRepository, ApprovalRepository approvalRepository, UserRepository userRepository) {
        this.detailRepository = detailRepository;
        this.productListRepository = productListRepository;
        this.approvalRepository = approvalRepository;
        this.userRepository = userRepository;
    }

    public List<DetailDTO> getDetailsByProductNumTop10(Long productNum) { // reconditioned 날짜순 10개 정렬
        List<Detail> details = detailRepository.findByProductListProductNum(productNum);
        return details.stream()
                .map(detail -> new DetailDTO(
                        detail.getId(),
                        detail.getProductNum(),
                        detail.getProductName(),
                        detail.getDate(),
                        detail.getSerialNum(),
                        detail.getApproval().getWorker().getName(),
                        detail.getApproval().getMiddleManager().getName(),
                        detail.getApproval().getLastManager().getName(),
                        detail.getApproval().getWorker().getDepartment().getDepartmentName(),
                        detail.getMemo(),
                        detail.getApproval().getApprovalStatus()
                ))
                .sorted((d1, d2) -> d2.getDate().compareTo(d1.getDate()))
                .limit(10)
                .collect(Collectors.toList());
    }


    public Detail getDetailById(Long id) {
        return detailRepository.findById(id).orElse(null);
    }

    public DetailDTO toDetailWithFileDTO(Detail detail) {
        if (detail == null) {
            return null;
        }
        DetailDTO dtoWithFile = new DetailDTO();
        dtoWithFile.setId(detail.getId());
        dtoWithFile.setProductNum(detail.getProductNum());
        dtoWithFile.setProductName(detail.getProductName());
        dtoWithFile.setDate(detail.getDate());
        dtoWithFile.setSerialNum(detail.getSerialNum());
        dtoWithFile.setWorkerName(detail.getApproval().getWorker().getName());
        dtoWithFile.setMiddleManagerName(detail.getApproval().getMiddleManager().getName());
        dtoWithFile.setDeprtmentName(detail.getApproval().getWorker().getDepartment().getDepartmentName());
        dtoWithFile.setUrl1(detail.getFileUrl().getUrl1());
        dtoWithFile.setUrl2(detail.getFileUrl().getUrl2());
        dtoWithFile.setApprovalStatus(detail.getApproval().getApprovalStatus());

        return dtoWithFile;
    }

    // DTO 변환 메서드
    public DetailDTO toDetailListDTO(Detail detail) {
        if (detail == null) {
            return null;
        }
        DetailDTO dto = new DetailDTO();
        dto.setId(detail.getId());
        dto.setProductNum(detail.getProductNum());
        dto.setProductName(detail.getProductName());
        dto.setDate(detail.getDate());
        dto.setSerialNum(detail.getSerialNum());
        dto.setWorkerName(detail.getApproval().getMiddleManager().getName());
        dto.setMiddleManagerName(detail.getApproval().getMiddleManager().getName());
        dto.setDeprtmentName(detail.getApproval().getWorker().getDepartment().getDepartmentName());
        dto.setMemo(detail.getMemo());
        return dto;
    }


}

