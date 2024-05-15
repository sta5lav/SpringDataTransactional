package com.example.springdatatransactional.dto;



import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceOrderDto {

    private long id;
    private long customerId;
    private int[] productId;
    private BigDecimal totalAmount;


}
