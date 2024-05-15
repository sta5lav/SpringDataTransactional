package com.example.springdatatransactional.service;

import com.example.springdatatransactional.dto.OrderDto;
import com.example.springdatatransactional.dto.PlaceOrderDto;
import com.example.springdatatransactional.mapper.OrderMapper;
import com.example.springdatatransactional.model.Order;
import com.example.springdatatransactional.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CustomerService customerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public OrderDto getOrderInfo(Long id) {
        return orderMapper.getOrderDtoFromOrder(orderRepository.findById(id).orElse(null));
    }

    @Override
    public List<OrderDto> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::getOrderDtoFromOrder).collect(Collectors.toList());
    }

    @Override
    public boolean addOrder(OrderDto orderDto) {
        if (orderDto != null) {
            orderRepository.save(orderMapper.getOrderFromOrderDto(orderDto));
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrder(OrderDto orderDto) {
        if (orderRepository.existsById(orderDto.getId())) {
            Order order = orderRepository.findById(orderDto.getId()).get();
            order.setCustomer(orderDto.getCustomer());
            order.setProducts(orderDto.getProducts());
            order.setTotalAmount(orderDto.getTotalAmount());
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public OrderDto placeOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomer(customerService.setBalanceCustomer(orderDto.getCustomer().getId(), orderDto.getTotalAmount()));
        order.setProducts(productService.setQuantityProduct(orderDto.getProducts()));
        order.setTotalAmount(orderDto.getTotalAmount());
        System.out.println(order);
        orderRepository.save(order);
        return orderDto;
    }
}
