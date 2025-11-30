package com.sopheak.restfulapi001.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    @Column(name = "customer_name", length = 300, nullable = false, unique = true)
    private String customerName;
    private String email;
    private String password;
    private Boolean isDeleted;

//    @OneToMany(mappedBy = "customer",
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)

//    private Set<Order> orders = new HashSet<>();
}
