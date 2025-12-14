package com.sopheak.restfulapi001.service;
import com.sopheak.restfulapi001.entities.Category;
import com.sopheak.restfulapi001.entities.Product;
import com.sopheak.restfulapi001.exception.customexception.CustomException;
import com.sopheak.restfulapi001.mapper.ProductMapStruct;
import com.sopheak.restfulapi001.model.dto.CreateProductDto;
import com.sopheak.restfulapi001.model.dto.ProductResponseDto;
import com.sopheak.restfulapi001.model.dto.UpdateProductDto;
import com.sopheak.restfulapi001.repository.CategoryRepository;
import com.sopheak.restfulapi001.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapStruct productMapStruct;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductResponseDto> getAllProductsByPagination(Pageable pageable) {
        Page<Product> productPage = productRepository.findProductByIsDeletedIsFalse(pageable);
        return productPage.map(productMapStruct::mapFromProductToProductResponseDto);
    }

    @Override
    public ProductResponseDto getProductByUuid(String uuid) {
        Optional<Product> product = productRepository.findProductByUuid(uuid);
        if(product.isEmpty()){
            throw new CustomException("Product not found with uuid: " + uuid);
        }
        System.out.println("Fetched productName: " + product.get().getProductName());
        return productMapStruct.mapFromProductToProductResponseDto(product.get());
    }

    @Override
    public ProductResponseDto createProduct(CreateProductDto createProductDto) {

        Product product = new Product();

        product.setUuid(UUID.randomUUID().toString());
        product.setProductName(createProductDto.productName());
        product.setCreateDate(Date.from(Instant.now()));
        Date expireDate = Date.from(createProductDto.expireDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        product.setExpireDate(expireDate);
        product.setIsDeleted(false);

        Set<Category> categories = new HashSet<>();

        for(String categoryName: createProductDto.categoriesName()){
            Category category = categoryRepository.findCategoryByCategoryName(categoryName).orElseThrow();
            if(category.getIsDeleted().equals(true)){
                throw new RuntimeException("Category does not exist");
            }
            categories.add(category);
        }
        product.getCategories().addAll(categories);
        productRepository.save(product);
        return productMapStruct.mapFromProductToProductResponseDto(product);
    }

    @Transactional
    @Override
    public String deleteProductByUuid(String uuid) {
        Optional<Product> product = productRepository.findProductByUuid(uuid);
        if(product.isEmpty()){
            throw new CustomException("Product not found with uuid:  " + uuid);
        }
        product.get().setIsDeleted(true);
        productRepository.save(product.get());
        return uuid;
    }

    @Override
    public ProductResponseDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto) {
        Optional<Product> product = productRepository.findProductByUuid(uuid);
        if(product.isEmpty()){
            throw new CustomException("Product not found with uuid: " + uuid);
        }
        product.get().setProductName(updateProductDto.productName());
        Date expireDate = Date.from(updateProductDto.expireDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        product.get().setExpireDate(expireDate);
        productRepository.save(product.get());
        return productMapStruct.mapFromProductToProductResponseDto(product.get());
    }
}
