package com.sopheak.restfulapi001.controller;
import com.sopheak.restfulapi001.model.dto.CreateCustomerDto;
import com.sopheak.restfulapi001.model.dto.UpdateCustomerDto;
import com.sopheak.restfulapi001.service.CustomerService;
import com.sopheak.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/pagination")
    public ResponseTemplate<Object> getAllCustomersByPagination(Pageable pageable){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get customers data by pagination successfully")
                .data(customerService.getAllCustomersByPagination((pageable)))
                .build();
    }

    @GetMapping("/get-all-customers-data")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTemplate<Object> getAllCustomers(){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .staus(HttpStatus.OK.toString())
                .message("Get all customers data successfully")
                .data(customerService.getAllCustomers())
                .build();
    }

    @GetMapping("/get-a-customer-data-by-uuid/{uuid}")
    public ResponseTemplate<Object> getCustomerByUUID(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .staus(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Get a customer by uuid successfully")
                .data(customerService.getCustomerByUuid(uuid))
                .build();
    }

    @PostMapping("/create-a-new-customer-data")
    public ResponseTemplate<Object> createNewCustomer(@RequestBody @Validated CreateCustomerDto createCustomerDto){
        return ResponseTemplate
                .builder()
                .staus(HttpStatus.CREATED.toString())
                .date(Date.from(Instant.now()))
                .message("Create a customer successfully")
                .data(customerService.createNewCustomer(createCustomerDto))
                .build();
    }

    @DeleteMapping("/delete-a-customer-data-by-uuid/{uuid}")
    public ResponseTemplate<Object> deleteCustomerByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .staus(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Delete a customer successfully")
                .data(customerService.deleteCustomerByUuid(uuid))
                .build();
    }

    @PutMapping("/update-a-customer-by-uuid/{uuid}")
    public ResponseTemplate<Object> updateCustomerByUuid(@PathVariable String uuid, @RequestBody @Validated UpdateCustomerDto updateCustomerDto){
        return ResponseTemplate
                .builder()
                .staus(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Update a customer successfully")
                .data(customerService.updateCustomer(uuid, updateCustomerDto))
                .build();
    }
}
