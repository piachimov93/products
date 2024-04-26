package com.piachimov.productservice.utils;

import com.piachimov.productservice.model.Product;
import com.piachimov.productservice.dto.ProductResponse;

import java.util.function.Function;

public class ProductUtils {

    public static Function<Product, ProductResponse> convertToProductResponse = product -> new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()
    );
}
