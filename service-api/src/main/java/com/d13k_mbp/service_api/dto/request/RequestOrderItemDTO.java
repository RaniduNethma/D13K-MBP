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
    private int itemDiscount;
    private BigInteger itemQuantity;
    private String productId;
    private String pricelistId;
}
