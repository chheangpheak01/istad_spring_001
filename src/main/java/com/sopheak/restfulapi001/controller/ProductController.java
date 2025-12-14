package com.sopheak.restfulapi001.controller;
import com.sopheak.restfulapi001.model.dto.CreateProductDto;
import com.sopheak.restfulapi001.model.dto.UpdateProductDto;
import com.sopheak.restfulapi001.service.ProductService;
import com.sopheak.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/pagination")
    public ResponseTemplate<Object> getAllProductByPagination(Pageable pageable){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get product data by pagination successfully")
                .data(productService.getAllProductsByPagination(pageable))
                .build();
    }
    @GetMapping("/get-a-product-data-by-uuid/{uuid}")
    public ResponseTemplate<Object> getProductByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get a product data successfully")
                .data(productService.getProductByUuid(uuid))
                .build();
    }

    @PostMapping("/create-a-new-product-data")
    public ResponseTemplate<Object> createProduct(@RequestBody @Validated CreateProductDto createProductDto){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Create a product data successfully")
                .data(productService.createProduct(createProductDto))
                .build();
    }

    @DeleteMapping("/delete-a-product-data-by-uuid/{uuid}")
    public ResponseTemplate<Object> deleteProductByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Delete a product data successfully")
                .data(productService.deleteProductByUuid(uuid))
                .build();
    }

    @PutMapping("/update-a-product-data-by-uuid/{uuid}")
    public ResponseTemplate<Object> updateProductByUuid(@PathVariable String uuid, @RequestBody @Validated UpdateProductDto updateProductDto){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Update a product data successfully")
                .data(productService.updateProductByUuid(uuid, updateProductDto))
                .build();
    }
}
