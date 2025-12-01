package com.sadcodes.productservice.service.impl;

import com.sadcodes.productservice.dto.ProductRequestDto;
import com.sadcodes.productservice.dto.ProductResponseDto;
import com.sadcodes.productservice.entity.Category;
import com.sadcodes.productservice.entity.Product;
import com.sadcodes.productservice.repository.CategoryRepository;
import com.sadcodes.productservice.repository.ProductRepository;
import com.sadcodes.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return convertToDto(saved);
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public ProductResponseDto updateStock(Long productId, Integer stockQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStockQuantity(stockQuantity);
        Product updated = productRepository.save(product);

        return convertToDto(updated);
    }


    private ProductResponseDto convertToDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();

        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setInStock(product.getInStock());
        dto.setCategoryName(product.getCategory().getName());

        return dto;
    }
}
