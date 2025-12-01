package com.sadcodes.productservice.service;

import com.sadcodes.productservice.dto.ProductRequestDto;
import com.sadcodes.productservice.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(Long productId);
    List<ProductResponseDto> getAllProduct();
    ProductResponseDto updateStock(Long productId, Integer stockQuantity);

}
