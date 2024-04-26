package com.piachimov.productservice.service;

import com.piachimov.productservice.model.Product;
import com.piachimov.productservice.dto.ProductRequest;
import com.piachimov.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        log.info("Product successfully created");
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
