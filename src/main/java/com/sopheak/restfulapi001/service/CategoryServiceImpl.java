package com.sopheak.restfulapi001.service;
import com.sopheak.restfulapi001.entities.Category;
import com.sopheak.restfulapi001.exception.customexception.CustomException;
import com.sopheak.restfulapi001.mapper.CategoryMapStruct;
import com.sopheak.restfulapi001.model.dto.CategoryResponseDto;
import com.sopheak.restfulapi001.model.dto.CreateCategoryDto;
import com.sopheak.restfulapi001.model.dto.UpdateCategoryDto;
import com.sopheak.restfulapi001.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    final CategoryRepository categoryRepository;
    final CategoryMapStruct categoryMapStruct;

    @Override
    public Page<CategoryResponseDto> getAllCategoriesByPagination(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findCategoriesByIsDeletedFalse(pageable);
        return categoryPage.map(categoryMapStruct::mapFromCategoryToCategoryResponseDto);
    }

    @Override
    public CategoryResponseDto getCategoryByUuid(String uuid) {
        Optional<Category> category = categoryRepository.findCategoryByUuid(uuid);
        if(category.isEmpty()){
            throw new CustomException("Category not found");
        }
        return categoryMapStruct.mapFromCategoryToCategoryResponseDto(category.get());
    }

    @Override
    public CategoryResponseDto createCategory(CreateCategoryDto createCategoryDto) {
        Category category = new Category();
        category.setUuid(UUID.randomUUID().toString());
        category.setCategoryName(createCategoryDto.categoryName());
        category.setCreatedDate(Date.from(Instant.now()));
        category.setIsDeleted(false);
        categoryRepository.save(category);
        return categoryMapStruct.mapFromCategoryToCategoryResponseDto(category);
    }

    @Override
    public String deleteCategoryByUuid(String uuid) {
        Optional<Category> category = categoryRepository.findCategoryByUuid(uuid);
        if(category.isEmpty()){
            throw new CustomException("Category not found");
        }
        categoryRepository.delete(category.get());
        return uuid;
    }

    @Override
    public CategoryResponseDto updateCategoryByUuid(String uuid, UpdateCategoryDto updateCategoryDto) {
        Optional<Category> category = categoryRepository.findCategoryByUuid(uuid);
        if(category.isEmpty()){
            throw new CustomException("Category not found");
        }
        category.get().setCategoryName(updateCategoryDto.categoryName());
        categoryRepository.save(category.get());
        return categoryMapStruct.mapFromCategoryToCategoryResponseDto(category.get());
    }
}
