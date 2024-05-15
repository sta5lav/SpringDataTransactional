package com.example.springdatatransactional.repository;

import com.example.springdatatransactional.model.Customer;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Customer> findById(Long id);

}
