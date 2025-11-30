package com.sopheak.restfulapi001.service;
import com.sopheak.restfulapi001.entities.Customer;
import com.sopheak.restfulapi001.entities.Order;
import com.sopheak.restfulapi001.exception.customexception.CustomException;
import com.sopheak.restfulapi001.mapper.CustomerMapStruct;
import com.sopheak.restfulapi001.model.dto.CreateCustomerDto;
import com.sopheak.restfulapi001.model.dto.CustomerResponseDto;
import com.sopheak.restfulapi001.model.dto.UpdateCategoryDto;
import com.sopheak.restfulapi001.model.dto.UpdateCustomerDto;
import com.sopheak.restfulapi001.repository.CustomerRepository;
import com.sopheak.restfulapi001.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapStruct customerMapStruct;
    private final OrderRepository orderRepository;

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDto = new ArrayList<>();
        customers.forEach(c -> customerResponseDto.add(customerMapStruct.mapFromCustomerToCustomerResponseDto(c)));
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto getCustomerByUuid(String uuid) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomersByUuid(uuid));
        if(customer.isEmpty()){
            throw new CustomException("Customer is not found");
        }
        return customerMapStruct.mapFromCustomerToCustomerResponseDto(customerRepository.findCustomersByUuid(uuid));
    }

    @Override
    public CustomerResponseDto createNewCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = new Customer();
        customer.setUuid(UUID.randomUUID().toString());
        customer.setCustomerName(createCustomerDto.customerName());
        customer.setEmail(createCustomerDto.email());
        customer.setPassword(createCustomerDto.password());
        customer.setIsDeleted(false);
        customerRepository.save(customer);
        return customerMapStruct.mapFromCustomerToCustomerResponseDto(customer);
    }

    @Transactional
    @Override
    public String deleteCustomerByUuid(String uuid) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomersByUuid(uuid));
        if(customer.isEmpty()){
            throw new CustomException("Customer is not found");
        }
        List<Order> orders = orderRepository.findOrderByCustomerId(customer.get().getId());
        orderRepository.deleteAll(orders);
        customerRepository.delete(customer.get());
        return uuid;
    }

    @Override
    public Page<CustomerResponseDto> getAllCustomersByPagination(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findCustomersByIsDeletedIsFalse(pageable);
        return customerPage.map(customerMapStruct::mapFromCustomerToCustomerResponseDto);
    }

    @Override
    public CustomerResponseDto updateCustomer(String uuid, UpdateCustomerDto updateCustomerDto) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomersByUuid(uuid));
        if(customer.isEmpty()){
            throw new CustomException("customer is not found");
        }
        customer.get().setCustomerName(updateCustomerDto.customerName());
        customer.get().setEmail(updateCustomerDto.email());
        customerRepository.save(customer.get());
        return customerMapStruct.mapFromCustomerToCustomerResponseDto(customer.get());
    }
}
