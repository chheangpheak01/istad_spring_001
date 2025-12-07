package com.sopheak.restfulapi001.service;
import com.sopheak.restfulapi001.model.dto.CreateProductDto;
import com.sopheak.restfulapi001.model.dto.ProductResponseDto;
import com.sopheak.restfulapi001.model.dto.UpdateProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Page<ProductResponseDto> getAllProductsByPagination(Pageable pageable);
    ProductResponseDto getProductByUuid(String uuid);
    ProductResponseDto createProduct(CreateProductDto createProductDto);
    String deleteProductByUuid(String uuid);
    ProductResponseDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto);
}
