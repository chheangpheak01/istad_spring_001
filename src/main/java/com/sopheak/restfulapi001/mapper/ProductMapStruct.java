package com.sopheak.restfulapi001.mapper;
import com.sopheak.restfulapi001.entities.Product;
import com.sopheak.restfulapi001.model.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapStruct {
    @Mapping(source = "uuid",target = "uuid",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ProductResponseDto mapFromProductToProductResponseDto(Product product);
}
