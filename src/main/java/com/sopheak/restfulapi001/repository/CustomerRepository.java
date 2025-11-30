package com.sopheak.restfulapi001.repository;
import com.sopheak.restfulapi001.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomersByUuid(String uuid);
    Page<Customer> findCustomersByIsDeletedIsFalse(Pageable pageable);
}