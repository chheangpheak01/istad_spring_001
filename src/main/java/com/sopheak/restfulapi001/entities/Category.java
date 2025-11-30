package com.sopheak.restfulapi001.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    @Column(length = 200, nullable = false)
    private String categoryName;
    private Date createdDate;
    //@Column(insertable = false)
    private Boolean isDeleted;
}
