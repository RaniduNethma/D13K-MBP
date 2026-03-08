package com.d13k_mbp.service_api.dto.request;

import lombok.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderItemDTO {
    private BigDecimal pricePerUnit;
    private int itemDiscount;
    private BigInteger itemQuantity;
    private BigInteger totalPrice;
    private String productId;
    private String orderId;
    private String pricelistId;
}
