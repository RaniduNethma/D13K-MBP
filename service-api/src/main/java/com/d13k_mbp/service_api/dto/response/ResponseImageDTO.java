package com.d13k_mbp.service_api.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseImageDTO {
    private long imageId;
    private String productId;
    private String hash;
    private String resourceURL;
    private String directory;
    private String fileName;
}
