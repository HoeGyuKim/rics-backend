package com.example.repairproductprogram.service;

import com.example.repairproductprogram.NotFoundException.ProductListNotFoundException;
import com.example.repairproductprogram.dto.DetailDTO;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.ProductList;
import com.example.repairproductprogram.repository.DetailRepository;
import com.example.repairproductprogram.repository.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailService {
    private final DetailRepository detailRepository;
    private final ProductListRepository productListRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository, ProductListRepository productListRepository) {
        this.detailRepository = detailRepository;
        this.productListRepository = productListRepository;
    }

    public List<DetailDTO> getDetailsByProductNumAndRdTrue(Long productNum) {
        List<Detail> details = detailRepository.findByProductListProductNumAndRdTrue(productNum);
        return details.stream()
                .map(this::toDetailListDTO)
                .collect(Collectors.toList());
    }

    public Detail getDetailById(Long id) {
        return detailRepository.findById(id).orElse(null);
    }

    public Detail saveDetail(Detail detail) {
        Long productNum = detail.getProductNum();
        if (productNum != null && productNum > 0) {
            ProductList productList = productListRepository.findById(productNum)
                    .orElseThrow(() -> new ProductListNotFoundException("ProductList with productNum " + productNum + " not found."));
            detail.setProductNum(productList.getProductNum());
            detail.setProductName(productList.getProductNum());
        }
        return detailRepository.save(detail);
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
        dtoWithFile.setWorker(detail.getWorker().getName());
        dtoWithFile.setManager(detail.getManager().getName());
        dtoWithFile.setDeaprtmentName(detail.getWorker().getDepartment().getDepartmentName());
        dtoWithFile.setUrl1(detail.getFileUrl1());
        dtoWithFile.setUrl2(detail.getFileUrl2());

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
        dto.setWorker(detail.getWorker().getName());
        dto.setManager(detail.getManager().getName());
        dto.setDeaprtmentName(detail.getWorker().getDepartment().getDepartmentName());
        return dto;
    }
}
