package com.example.springdatatransactional.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerDto {

    private long id;
    private String username;
    private BigDecimal balance;


}
