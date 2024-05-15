package com.example.springdatatransactional.service;

import com.example.springdatatransactional.dto.ProductDto;
import com.example.springdatatransactional.mapper.ProductMapper;
import com.example.springdatatransactional.model.Product;
import com.example.springdatatransactional.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto getProductInfo(Long id) {
        return productMapper.getProductDtoFromProduct(productRepository.findById(id).orElse(null));
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::getProductDtoFromProduct).collect(Collectors.toList());
    }

    @Override
    public boolean addProduct(ProductDto productDto) {
        if (productDto != null) {
            productRepository.save(productMapper.getProductFromProductDto(productDto));
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        if (productRepository.existsById(productDto.getId())) {
            Product product = productRepository.findById(productDto.getId()).get();
            product.setProductName(productDto.getProductName());
            product.setQuantity(productDto.getQuantity());
            product.setPrice(productDto.getPrice());
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Map<Long, Boolean> checkProductsOnQuantity(List<Product> products) {
        //Результирующая карта, в которой ключ это айди товара, а значение - хватает ли товара на складе
        Map<Long, Boolean> resultMapCompareQuantityProducts = new HashMap<>();

        for (Product s : products) {

            //Получаем продукт из репозитория на основе айди продукта в ордере
            Product productOnRepository = productRepository.findWithLockById(s.getId());

            //Проверяем есть ли в наличии данный продукт и хватает ли его для оформления заказа
            if(productOnRepository == null || productOnRepository.getQuantity() < s.getQuantity()){
                resultMapCompareQuantityProducts.put(s.getId(), false);
            } else resultMapCompareQuantityProducts.put(s.getId(), true);

        }
        return resultMapCompareQuantityProducts;
    }

    @Transactional
    @Override
    public List<Product> setQuantityProduct(List<Product> products) {
        if (checkProductsOnQuantity(products).containsValue(false)) {
            return null;
        }
        List<Product> resultProducts = new ArrayList<>();
        for (Product s : products) {
            Product productOnRepository = productRepository.findById(s.getId()).get();
            productOnRepository.setQuantity(productOnRepository.getQuantity() - s.getQuantity());
            resultProducts.add(productOnRepository);
            productRepository.save(productOnRepository);
        }
        return resultProducts;
    }


}
