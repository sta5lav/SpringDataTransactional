package com.example.springdatatransactional.repository;

import com.example.springdatatransactional.model.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();


    Optional<Product> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Product findWithLockById(Long id);
}
