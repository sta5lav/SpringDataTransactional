package com.example.springdatatransactional.controller;

import com.example.springdatatransactional.dto.ProductDto;
import com.example.springdatatransactional.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/product")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductInfo(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(
                productService.addProduct(productDto) ?
                        HttpStatus.CREATED:
                        HttpStatus.BAD_REQUEST
        ).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(
                productService.updateProduct(productDto) ?
                        HttpStatus.CREATED:
                        HttpStatus.BAD_REQUEST
        ).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id) {
        return ResponseEntity.status(
                productService.deleteProduct(id) ?
                        HttpStatus.OK:
                        HttpStatus.NOT_FOUND
        ).build();
    }


}
