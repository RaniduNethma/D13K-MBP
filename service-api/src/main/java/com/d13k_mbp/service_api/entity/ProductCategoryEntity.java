package com.d13k_mbp.service_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryEntity {

    @Id
    @Column(name = "category_id", nullable = false, length = 80)
    private String categoryId;

    @Column(name = "category_name", nullable = false, length = 250)
    private String categoryName;

    @Column(name = "category_description", nullable = false, length = 750)
    @Lob
    private Blob categoryDescription;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "ProductCategoryEntity", cascade = CascadeType.ALL)
    private List<ProductEntity> products;
}
