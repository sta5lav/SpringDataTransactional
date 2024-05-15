package com.example.springdatatransactional.controller;

import com.example.springdatatransactional.dto.OrderDto;
import com.example.springdatatransactional.dto.PlaceOrderDto;
import com.example.springdatatransactional.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderInfo(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrder() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrder());
    }

    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.status(
                orderService.addOrder(orderDto) ?
                        HttpStatus.CREATED:
                        HttpStatus.BAD_REQUEST
        ).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.status(
                orderService.updateOrder(orderDto) ?
                        HttpStatus.CREATED:
                        HttpStatus.BAD_REQUEST
        ).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.status(
                orderService.deleteOrder(id) ?
                        HttpStatus.OK:
                        HttpStatus.NOT_FOUND
        ).build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.placeOrder(orderDto));
    }

}
