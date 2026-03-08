package com.d13k_mbp.service_api.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePricelistItemDTO {
    private String pricelistItemId;
    private String productId;
    private String pricelistId;
    private BigDecimal price;
    private LocalDateTime createdAt;
}
