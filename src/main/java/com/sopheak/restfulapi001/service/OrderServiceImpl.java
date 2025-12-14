package com.sopheak.restfulapi001.service;
import com.sopheak.restfulapi001.entities.Customer;
import com.sopheak.restfulapi001.entities.Order;
import com.sopheak.restfulapi001.entities.OrderStatus;
import com.sopheak.restfulapi001.entities.Product;
import com.sopheak.restfulapi001.mapper.OrderMapStruct;
import com.sopheak.restfulapi001.model.dto.OrderCreateDto;
import com.sopheak.restfulapi001.model.dto.OrderResponseDto;
import com.sopheak.restfulapi001.repository.CustomerRepository;
import com.sopheak.restfulapi001.repository.OrderRepository;
import com.sopheak.restfulapi001.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderMapStruct orderMapStruct;

    @Override
    public Page<OrderResponseDto> getAllOrdersByPagination(Pageable pageable) {
        Page<Order> orderPagination = orderRepository.findAllByIsDeletedIsFalse(pageable);
        return orderPagination.map(orderMapStruct::mapFromOrderToOrderResponseDto);
    }

    @Override
    public List<OrderResponseDto> getOrderByCustomerUuid(String uuid) {
        Optional<List<Order>> orders = orderRepository.findAllByCustomer_UuidAndIsDeletedIsFalse(uuid);
        if(orders.isEmpty()){
            throw new RuntimeException("No order for this customer");
        }
        return orders.get().stream().map(orderMapStruct::mapFromOrderToOrderResponseDto).toList();
    }

    @Override
    public OrderResponseDto createOrder(OrderCreateDto orderCreateDto) {

        Order order = new Order();

        order.setUuid(UUID.randomUUID().toString());
        order.setOrderName(orderCreateDto.orderName());
        order.setOrderedDate(Date.from(Instant.now()));
        order.setIsDeleted(false);
        order.setStatus(OrderStatus.PENDING.toString());
        order.setLocation(orderCreateDto.location());

        Set<Product> products = new HashSet<>();

        for (String pUuid: orderCreateDto.productUuids()){
            Optional<Product> p = productRepository.findProductByUuid(pUuid);
            if(p.isPresent() && p.get().getIsDeleted().equals(true)){
                throw new RuntimeException("Product is deleted");
            }else if(p.isEmpty()){
                throw  new RuntimeException("Product not found");
            }
            products.add(p.get());
        }
        order.getProducts().addAll(products);

        Customer customer = customerRepository.findCustomersByUuid(orderCreateDto.customerUuid());

        if(customer == null){
            throw new RuntimeException("Customer not found");
        }
        order.setCustomer(customer);
        orderRepository.save(order);
        return orderMapStruct.mapFromOrderToOrderResponseDto(order);
    }

    @Override
    public String deleteOrderByUuid(String uuid) {

        Optional<Order> order = orderRepository.findOrderByUuid(uuid);

        if(order.isEmpty()){
            throw new RuntimeException("Order not found");
        }else if(order.get().getIsDeleted().equals(true)){
            throw new RuntimeException("The order wanted to delete is already deleted");
        }
        order.get().setIsDeleted(true);
        orderRepository.save(order.get());
        return order.get().getUuid();
    }

    @Override
    public OrderResponseDto getOrderByUuid(String uuid) {
        Optional<Order> order = orderRepository.findOrderByUuid(uuid);
        if(order.isEmpty()){
            throw new RuntimeException("Order not found");
        }
        return orderMapStruct.mapFromOrderToOrderResponseDto(order.get());
    }

    @Override
    public Boolean updateOrderStatus(String status, String orderUuid) {
        if(status.equalsIgnoreCase("PENDING") || status.equalsIgnoreCase("CANCELLED") || status.equalsIgnoreCase("COMPLETED")){
            Optional<Order> order = orderRepository.findOrderByUuid(orderUuid);
            if(order.isEmpty()){
                throw new RuntimeException("Order is not found");
            }else if(order.get().getIsDeleted()){
                throw new RuntimeException("Order has been deleted");
            }
            order.get().setStatus(status);
            orderRepository.save(order.get());
            return true;
        }
        throw new RuntimeException("Your status is wrong, you must in three values: [PENDING, CANCELLED, COMPLETED]");
    }
}
