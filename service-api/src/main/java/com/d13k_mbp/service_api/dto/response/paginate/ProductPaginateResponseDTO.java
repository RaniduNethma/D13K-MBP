package com.d13k_mbp.service_api.dto.response.paginate;

import com.d13k_mbp.service_api.dto.response.ResponseProductDTO;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPaginateResponseDTO {
    private List<ResponseProductDTO> productDataList;
    private long productDataCount;
}
