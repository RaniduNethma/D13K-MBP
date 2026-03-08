package com.d13k_mbp.service_api.dto.response.paginate;

import com.d13k_mbp.service_api.dto.response.ResponseOrderItemDTO;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemPaginateResponseDTO {
    private List<ResponseOrderItemDTO> orderItemDataList;
    private long orderItemDataCount;
}
