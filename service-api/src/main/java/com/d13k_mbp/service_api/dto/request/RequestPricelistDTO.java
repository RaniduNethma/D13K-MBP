package com.d13k_mbp.service_api.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPricelistDTO {
    private String pricelistName;
    private String pricelistDescription;
    private boolean isActive;
}
