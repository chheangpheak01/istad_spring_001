package com.sopheak.restfulapi001.mapper;
import com.sopheak.restfulapi001.entities.Category;
import com.sopheak.restfulapi001.model.dto.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapStruct {
    @Mapping(source = "uuid",target = "uuid",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    CategoryResponseDto mapFromCategoryToCategoryResponseDto(Category category);
}
