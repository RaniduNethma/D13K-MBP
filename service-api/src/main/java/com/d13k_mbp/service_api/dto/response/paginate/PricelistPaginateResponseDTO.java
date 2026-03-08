package com.d13k_mbp.service_api.dto.response.paginate;

import com.d13k_mbp.service_api.dto.response.ResponsePricelistDTO;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricelistPaginateResponseDTO {
    private List<ResponsePricelistDTO> pricelistDataList;
    private long pricelistDataCount;
}
