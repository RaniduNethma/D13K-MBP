package com.d13k_mbp.service_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pricelist")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricelistEntity {

    @Id
    @Column(name = "pricelist_id", nullable = false, length = 80)
    private String pricelistId;

    @Column(name = "pricelist_name", nullable = false, length = 250)
    private String pricelistName;

    @Column(name = "pricelist_description", nullable = false, length = 750)
    @Lob
    private Blob pricelistDescription;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "PricelistEntity", cascade = CascadeType.ALL)
    private List<PricelistItemEntity> pricelistItems;

    @OneToMany(mappedBy = "PricelistEntity", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;
}
