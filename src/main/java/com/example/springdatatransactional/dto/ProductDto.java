package com.example.springdatatransactional.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private long id;
    private String productName;
    private BigDecimal price;
    private int quantity;

}
