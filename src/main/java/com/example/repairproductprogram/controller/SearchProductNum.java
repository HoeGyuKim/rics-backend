package com.example.repairproductprogram.controller;

import com.example.repairproductprogram.model.ProductList;
import com.example.repairproductprogram.repository.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class SearchProductNum {

    @Autowired
    private ProductListRepository productListRepository;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductList product) {
        if (productListRepository.existsByProductNum(product.getProductNum())) {
            return new ResponseEntity<>("자재번호가 이미 존재합니다.", HttpStatus.BAD_REQUEST);
        }
        try {
            productListRepository.save(product);
            return new ResponseEntity<>("품목이 추가되었습니다.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("서버 오류 발생", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkProductExists(@RequestParam("productNum") int productNum) {
        boolean exists = productListRepository.existsByProductNum(productNum);
        return ResponseEntity.ok(exists);
    }
    @GetMapping
    public List<ProductList> getProducts(
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "filterBy", required = false) String filterBy) {

        if (searchTerm == null || searchTerm.isEmpty()) {
            return productListRepository.findAll();
        }

        if ("product_num".equals(filterBy)) {
            try {
                Integer product_num = Integer.parseInt(searchTerm);
                return productListRepository.findByProductNum(product_num);
            } catch (NumberFormatException e) {
                return Collections.emptyList();
            }
        } else if ("product_name".equals(filterBy)) {
            return productListRepository.findByProductName(searchTerm);
        }

        return Collections.emptyList();
    }
}
