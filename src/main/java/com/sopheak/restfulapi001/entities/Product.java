package com.sopheak.restfulapi001.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    @Column(name = "product_name", length = 300, nullable = false, updatable = false, unique = true)
    private String productName;
    private Date createDate;
    private Date expireDate;
    private Boolean isDeleted;

    @ManyToMany
    @JoinTable(
            name = "categories_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
}
