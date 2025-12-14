package com.sopheak.restfulapi001.controller;
import com.sopheak.restfulapi001.model.dto.OrderCreateDto;
import com.sopheak.restfulapi001.service.OrderService;
import com.sopheak.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/pagination")
    public ResponseTemplate<Object> getAllOrderByPagination(Pageable pageable){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get order data by pagination successfully")
                .data(orderService.getAllOrdersByPagination(pageable))
                .build();
    }

    @GetMapping("/get-order-by-customerUuid/{uuid}")
    public ResponseTemplate<Object> getOrderByCustomerUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get order data by customer's uuid successfully")
                .data(orderService.getOrderByCustomerUuid(uuid))
                .build();
    }

    @PostMapping("/create-order")
    public ResponseTemplate<Object> createOrder(@RequestBody OrderCreateDto orderCreateDto){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("create order data successfully")
                .data(orderService.createOrder(orderCreateDto))
                .build();
    }

    @DeleteMapping("/delete-order-by-uuid/{uuid}")
    public ResponseTemplate<Object> deleteOrderByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("delete order data by uuid successfully")
                .data(orderService.deleteOrderByUuid(uuid))
                .build();
    }

    @GetMapping("/get-order-by-uuid/{uuid}")
    public ResponseTemplate<Object> getOrderByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get order data by uuid successfully")
                .data(orderService.getOrderByUuid(uuid))
                .build();
    }

    @PutMapping("/update-order-status-by-uuid")
    public ResponseTemplate<Object> updateOrderStatus(@RequestParam(name = "orderUuid") String orderUuid, @RequestParam(name = "orderStatus") String orderStatus){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("update order status data by uuid successfully")
                .data(orderService.updateOrderStatus(orderStatus,orderUuid))
                .build();
    }
}
