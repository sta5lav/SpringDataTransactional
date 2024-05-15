package com.example.springdatatransactional.mapper;


import com.example.springdatatransactional.dto.OrderDto;
import com.example.springdatatransactional.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract OrderDto getOrderDtoFromOrder(Order order);
    public abstract Order getOrderFromOrderDto(OrderDto orderDto);

}
