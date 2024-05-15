package com.example.springdatatransactional.mapper;

import com.example.springdatatransactional.dto.ProductDto;
import com.example.springdatatransactional.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto getProductDtoFromProduct(Product product);
    Product getProductFromProductDto(ProductDto productDto);
}
