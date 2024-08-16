package com.example.repairproductprogram.service;

import com.example.repairproductprogram.NotFoundException.ProductListNotFoundException;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.ProductList;
import com.example.repairproductprogram.repository.DetailRepository;
import com.example.repairproductprogram.repository.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {

    private final DetailRepository detailRepository;
    private final ProductListRepository productListRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository, ProductListRepository productListRepository) {
        this.detailRepository = detailRepository;
        this.productListRepository = productListRepository;
    }

    public List<Detail> getAllDetail() {
        return detailRepository.findAll();
    }

    public Optional<Detail> getDetailById(Long id) {
        return detailRepository.findById(id);
    }

    public Detail saveDetail(Detail detail) {
        Integer productNum = detail.getProductNum();
        if (productNum != null && productNum > 0) {
            ProductList productList = productListRepository.findById(productNum)
                    .orElseThrow(() -> new ProductListNotFoundException("ProductList with productNum " + productNum + " not found."));
            detail.setProductList(productList);
        }
        return detailRepository.save(detail);
    }

    public void deleteDetail(Long id) {
        if (!detailRepository.existsById(id)) {
            throw new ProductListNotFoundException("Detail with ID " + id + " not found.");
        }
        detailRepository.deleteById(id);
    }
}
