package com.sopheak.restfulapi001.repository;
import com.sopheak.restfulapi001.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByUuid(String uuid);
    Page<Product> findProductByIsDeletedIsFalse(Pageable pageable);
}
