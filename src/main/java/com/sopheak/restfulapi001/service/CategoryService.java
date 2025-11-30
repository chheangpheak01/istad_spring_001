package com.sopheak.restfulapi001.service;
import com.sopheak.restfulapi001.model.dto.CategoryResponseDto;
import com.sopheak.restfulapi001.model.dto.CreateCategoryDto;
import com.sopheak.restfulapi001.model.dto.UpdateCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Page<CategoryResponseDto> getAllCategoriesByPagination(Pageable pageable);
    CategoryResponseDto getCategoryByUuid(String uuid);
    CategoryResponseDto createCategory(CreateCategoryDto createCategoryDto);
    String deleteCategoryByUuid(String uuid);
    CategoryResponseDto updateCategoryByUuid(String uuid, UpdateCategoryDto updateCategoryDto);
}
