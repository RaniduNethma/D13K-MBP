package com.d13k_mbp.service_api.dto.response.paginate;

import com.d13k_mbp.service_api.dto.response.ResponseImageDTO;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImagePaginateResponseDTO {
    private List<ResponseImageDTO> imageDataList;
    private long imageDataCount;
}
