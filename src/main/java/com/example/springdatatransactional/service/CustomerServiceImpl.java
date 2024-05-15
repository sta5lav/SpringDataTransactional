package com.example.springdatatransactional.service;

import com.example.springdatatransactional.model.Customer;
import com.example.springdatatransactional.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean checkBalanceForPurchase(Long id, BigDecimal bigDecimal) {
        if (getCustomerInfo(id) != null && getCustomerInfo(id).getBalance().compareTo(bigDecimal) >= 0) {
                return true;
        }
        return false;
    }

    @Override
    public Customer getCustomerInfo(Long id) {
        if (customerRepository.existsById(id)) {
            return customerRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    @Override
    public Customer setBalanceCustomer(Long id, BigDecimal bigDecimal) {
        if (!checkBalanceForPurchase(id, bigDecimal)) {
            return null;
        }
        Customer customer = customerRepository.findById(id).get();
        customer.setBalance(customer.getBalance().subtract(bigDecimal));
        customerRepository.save(customer);
        return customer;
    }
}
