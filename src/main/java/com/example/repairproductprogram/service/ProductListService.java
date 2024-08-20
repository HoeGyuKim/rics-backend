package com.example.repairproductprogram.service;

import com.example.repairproductprogram.repository.DetailRepository;
import com.example.repairproductprogram.repository.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductListService {
    private final ProductListRepository productListRepository;

    @Autowired
    public ProductListService(ProductListRepository productListRepository, DetailRepository detailRepository) {
        this.productListRepository = productListRepository;
    }
}
