package com.example.springdatatransactional.dto;

import com.example.springdatatransactional.model.Customer;
import com.example.springdatatransactional.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {

    private long id;
    private Customer customer;
    private List<Product> products;
    private BigDecimal totalAmount;

}
