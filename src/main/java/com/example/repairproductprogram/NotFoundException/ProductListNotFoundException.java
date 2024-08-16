package com.example.repairproductprogram.NotFoundException;

public class ProductListNotFoundException extends RuntimeException {
    public ProductListNotFoundException(String message) {
        super(message);
    }
}
