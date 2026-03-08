package com.d13k_mbp.service_api.dto.response.paginate;

import com.d13k_mbp.service_api.dto.response.ResponsePricelistItemDTO;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricelistItemPaginateResponseDTO {
    private List<ResponsePricelistItemDTO> pricelistItemDataList;
    private long pricelistItemDataCount;
}
