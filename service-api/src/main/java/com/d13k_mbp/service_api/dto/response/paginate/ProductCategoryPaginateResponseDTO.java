package com.d13k_mbp.service_api.dto.response.paginate;

import com.d13k_mbp.service_api.dto.response.ResponseProductCategoryDTO;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryPaginateResponseDTO {
    private List<ResponseProductCategoryDTO> productCategoryDataList;
    private long productCategoryDataCount;
}
