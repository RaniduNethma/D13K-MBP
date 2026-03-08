package com.d13k_mbp.service_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pricelist_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricelistItemEntity {

    @Id
    @Column(name = "pricelist_item_id", nullable = false, length = 80)
    private String pricelistItemId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricelist_id")
    private PricelistEntity pricelistEntity;
}
