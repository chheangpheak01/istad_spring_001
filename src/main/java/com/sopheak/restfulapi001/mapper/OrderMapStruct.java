package com.sopheak.restfulapi001.mapper;
import com.sopheak.restfulapi001.entities.Order;
import com.sopheak.restfulapi001.model.dto.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring")
public interface OrderMapStruct {
    @Mapping(source = "uuid",target = "uuid",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    OrderResponseDto mapFromOrderToOrderResponseDto(Order order);
}
