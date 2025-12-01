package com.sadcodes.productservice.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private Boolean inStock;
    private String categoryName;
}
