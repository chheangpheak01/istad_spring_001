package com.sopheak.restfulapi001.service;
import com.sopheak.restfulapi001.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CustomerService {
    List<CustomerResponseDto> getAllCustomers();
    CustomerResponseDto getCustomerByUuid(String uuid);
    CustomerResponseDto createNewCustomer(CreateCustomerDto createCustomerDto);
    String deleteCustomerByUuid(String uuid);
    Page<CustomerResponseDto> getAllCustomersByPagination(Pageable pageable);
    CustomerResponseDto updateCustomer(String uuid, UpdateCustomerDto updateCustomerDto);
}
