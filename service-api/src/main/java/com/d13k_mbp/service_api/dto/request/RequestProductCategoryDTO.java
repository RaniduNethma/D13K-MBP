package com.d13k_mbp.service_api.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductCategoryDTO {
    private String categoryName;
    private String categoryDescription;
    private boolean isActive;
}
