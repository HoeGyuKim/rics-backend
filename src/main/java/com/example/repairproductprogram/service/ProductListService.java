package com.example.repairproductprogram.service;

import com.example.repairproductprogram.dto.R_Dto;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.ProductList;
import com.example.repairproductprogram.repository.DetailRepository;
import com.example.repairproductprogram.repository.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductListService {

    private final ProductListRepository productListRepository;


    @Autowired
    public ProductListService(ProductListRepository productListRepository, DetailRepository detailRepository) {
        this.productListRepository = productListRepository;
    }

    public List<ProductList> getAllProducts() {
        return productListRepository.findAll();
    }

    public List<ProductList> findByProductNum(Integer productNum) {
        return productListRepository.findByProductNum(productNum);
    }

    public List<ProductList> findByProductName(String productName) {
        return productListRepository.findByProductName(productName);
    }

    public List<R_Dto> getSimpleProductList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productListRepository.findAll(pageable).stream()
                .map(productList -> new R_Dto(
                        productList.getProductNum(),
                        productList.getProductName() != null ? productList.getProductName() : "Unknown"))
                .collect(Collectors.toList());
    }

}
