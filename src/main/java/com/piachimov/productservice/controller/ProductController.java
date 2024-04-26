package com.piachimov.productservice.controller;

import com.piachimov.productservice.dto.ProductRequest;
import com.piachimov.productservice.dto.ProductResponse;
import com.piachimov.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.piachimov.productservice.utils.ProductUtils.convertToProductResponse;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return convertToProductResponse.apply(productService.createProduct(productRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts() {
        return productService.getProducts().stream()
                .map(convertToProductResponse)
                .toList();
    }
}
