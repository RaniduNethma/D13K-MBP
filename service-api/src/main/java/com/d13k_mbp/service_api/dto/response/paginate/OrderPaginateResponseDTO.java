package com.d13k_mbp.service_api.dto.response.paginate;

import com.d13k_mbp.service_api.dto.response.ResponseOrderDTO;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaginateResponseDTO {
    private List<ResponseOrderDTO> orderDataList;
    private long orderDataCount;
}
