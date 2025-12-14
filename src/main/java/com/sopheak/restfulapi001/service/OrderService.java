package com.sopheak.restfulapi001.service;
import com.sopheak.restfulapi001.model.dto.OrderCreateDto;
import com.sopheak.restfulapi001.model.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface OrderService {
    Page<OrderResponseDto> getAllOrdersByPagination(Pageable pageable);
    List<OrderResponseDto> getOrderByCustomerUuid(String uuid);
    OrderResponseDto createOrder(OrderCreateDto orderCreateDto);
    String deleteOrderByUuid(String uuid);
    OrderResponseDto getOrderByUuid(String uuid);
    Boolean updateOrderStatus(String status, String orderUuid);
}
