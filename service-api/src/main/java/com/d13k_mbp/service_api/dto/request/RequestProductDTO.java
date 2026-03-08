package com.d13k_mbp.service_api.dto.request;

import lombok.*;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductDTO {
    private String productName;
    private String productDescription;
    private boolean isActive;
    private BigInteger quantity;
    private int weight;
    private String categoryId;
}
