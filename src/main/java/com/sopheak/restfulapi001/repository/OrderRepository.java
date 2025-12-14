package com.sopheak.restfulapi001.repository;
import com.sopheak.restfulapi001.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrderByCustomerId(Integer CustomerId);
    Optional<Order> findOrderByUuid(String uuid);
    Page<Order> findAllByIsDeletedIsFalse(Pageable pageable);
    //Optional<List<Order>> findAllByCustomer_Uuid(String userUuid);
    Optional<List<Order>> findAllByCustomer_UuidAndIsDeletedIsFalse(String userUuid);

}
