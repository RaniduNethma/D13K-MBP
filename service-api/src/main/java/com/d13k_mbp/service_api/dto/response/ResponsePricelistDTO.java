package com.d13k_mbp.service_api.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePricelistDTO {
    private String pricelistId;
    private String pricelistName;
    private String pricelistDescription;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ResponsePricelistItemDTO> pricelistItems;
    private List<ResponseOrderItemDTO> orderItems;
}
