package com.d13k_mbp.service_api.dto.request;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPricelistItemDTO {
    private BigDecimal price;
    private String productId;
    private String pricelistId;
}
