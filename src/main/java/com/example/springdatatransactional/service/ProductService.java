package com.example.springdatatransactional.service;

import com.example.springdatatransactional.dto.ProductDto;
import com.example.springdatatransactional.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductDto getProductInfo(Long id);

    List<ProductDto> getAllProduct();

    boolean addProduct(ProductDto productDto);

    boolean updateProduct(ProductDto productDto);

    boolean deleteProduct(Long id);

    Map<Long, Boolean> checkProductsOnQuantity(List<Product> products);

    List<Product> setQuantityProduct(List<Product> products);
}
