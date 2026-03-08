package com.d13k_mbp.service_api.service.util;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseDTO {
    private int statusCode;
    private String message;
    private Object data;
}
