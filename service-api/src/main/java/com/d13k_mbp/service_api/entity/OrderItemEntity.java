package com.d13k_mbp.service_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @Column(name = "order_item_id", nullable = false, length = 80)
    private String orderItemId;

    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;

    @Column(name = "item_discount", nullable = false)
    private int ItemDiscount;

    @Column(name = "item_quantity", nullable = false)
    private BigInteger itemQuantity;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricelist_id")
    private PricelistEntity pricelist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
