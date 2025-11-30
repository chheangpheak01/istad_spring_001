package com.sopheak.restfulapi001.repository;
import com.sopheak.restfulapi001.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrderByCustomerId(Integer CustomerId);
}
