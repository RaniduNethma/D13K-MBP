package com.d13k_mbp.service_api.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestImageDTO {
    private MultipartFile file;
    private String productId;
}
