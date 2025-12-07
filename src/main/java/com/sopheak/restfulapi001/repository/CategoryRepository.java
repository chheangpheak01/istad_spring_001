package com.sopheak.restfulapi001.repository;
import com.sopheak.restfulapi001.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByUuid(String uuid);
    Page<Category> findCategoriesByIsDeletedFalse(Pageable pageable);
    Optional<Category> findCategoryByCategoryName(String name);
}
