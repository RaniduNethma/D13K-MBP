package com.d13k_mbp.service_api.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderItemDTO {
    private String orderItemId;
    private String productId;
    private String orderId;
    private String pricelistId;
    private BigDecimal pricePerUnit;
    private int itemDiscount;
    private BigInteger itemQuantity;
    private BigInteger totalPrice;
    private LocalDateTime createdAt;
}
