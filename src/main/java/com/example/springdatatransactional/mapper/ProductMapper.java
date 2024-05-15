package com.example.springdatatransactional.mapper;

import com.example.springdatatransactional.dto.ProductDto;
import com.example.springdatatransactional.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

   public abstract ProductDto getProductDtoFromProduct(Product product);
   public abstract Product getProductFromProductDto(ProductDto productDto);
}
