package com.d13k_mbp.service_api.dto.response;

import lombok.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {
    private String productId;
    private String categoryId;
    private String productName;
    private String productDescription;
    private boolean isActive;
    private BigInteger quantity;
    private int weight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ResponseOrderItemDTO> orderItems;
    private List<ResponseImageDTO> images;
    private List<ResponsePricelistItemDTO> pricelistItems;
}
