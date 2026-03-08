package com.d13k_mbp.service_api.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductCategoryDTO {
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ResponseProductDTO> products;
}
