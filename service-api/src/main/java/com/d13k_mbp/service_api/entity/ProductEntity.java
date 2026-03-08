package com.d13k_mbp.service_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity{

    @Id
    @Column(name = "product_id", nullable = false, length = 80)
    private String productId;

    @Column(name = "product_name", nullable = false, length = 250)
    private String productName;

    @Column(name = "product_description", nullable = false, length = 750)
    @Lob
    private Blob productDescription;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "quantity", nullable = true)
    private BigInteger quantity;

    @Column(name = "weight", nullable = true)
    private int weight;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategoryEntity productCategoryEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<PricelistItemEntity> pricelistItems;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<ImageEntity> images;
}
