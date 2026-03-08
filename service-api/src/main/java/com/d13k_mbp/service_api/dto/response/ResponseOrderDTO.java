package com.d13k_mbp.service_api.dto.response;

import com.d13k_mbp.service_api.enums.OrderStatusEnum;
import com.d13k_mbp.service_api.enums.PaymentMethodEnum;
import com.d13k_mbp.service_api.enums.PaymentStatusEnum;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDTO {
    private String orderId;
    private OrderStatusEnum orderStatus;
    private BigDecimal subTotal;
    private BigDecimal grandTotal;
    private int orderDiscount;
    private PaymentStatusEnum paymentStatus;
    private PaymentMethodEnum paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ResponseOrderItemDTO> orderItems;
}
