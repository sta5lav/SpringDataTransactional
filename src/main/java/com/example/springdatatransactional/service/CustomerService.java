package com.example.springdatatransactional.service;

import com.example.springdatatransactional.model.Customer;

import java.math.BigDecimal;

public interface CustomerService {

    boolean checkBalanceForPurchase(Long id, BigDecimal bigDecimal);

    Customer getCustomerInfo(Long id);

    boolean setBalanceCustomer(Long id, BigDecimal bigDecimal);
}
