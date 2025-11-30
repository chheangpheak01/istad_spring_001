package com.sopheak.restfulapi001.controller;
import com.sopheak.restfulapi001.model.dto.CreateCategoryDto;
import com.sopheak.restfulapi001.model.dto.UpdateCategoryDto;
import com.sopheak.restfulapi001.service.CategoryService;
import com.sopheak.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Date;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/pagination")
    public ResponseTemplate<Object> getAllCategoriesByPagination(Pageable pageable){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get category data by pagination successfully")
                .data(categoryService.getAllCategoriesByPagination(pageable))
                .build();
    }

    @GetMapping("/get-a-category-data-by-uuid/{uuid}")
    public ResponseTemplate<Object> getCustomerByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get a category data successfully")
                .data(categoryService.getCategoryByUuid(uuid))
                .build();
    }

    @PostMapping("/create-a-new-category-data")
    public ResponseTemplate<Object> createCategory(@RequestBody @Validated CreateCategoryDto createCategoryDto){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Create a category data successfully")
                .data(categoryService.createCategory(createCategoryDto))
                .build();
    }

    @DeleteMapping("/delete-a-category-data-by-uuid/{uuid}")
    public ResponseTemplate<Object> deleteCategoryByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Delete a category data successfully")
                .data(categoryService.deleteCategoryByUuid(uuid))
                .build();
    }

    @PutMapping("/update-a-category-data-by-uuid/{uuid}")
    public ResponseTemplate<Object> updateCategoryByUuid(@PathVariable String uuid, @RequestBody @Validated UpdateCategoryDto updateCategoryDto){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Update a category data successfully")
                .data(categoryService.updateCategoryByUuid(uuid, updateCategoryDto))
                .build();
    }

}

