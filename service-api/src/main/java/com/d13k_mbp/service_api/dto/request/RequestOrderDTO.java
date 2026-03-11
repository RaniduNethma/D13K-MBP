package com.d13k_mbp.service_api.dto.request;

import com.d13k_mbp.service_api.enums.OrderStatusEnum;
import com.d13k_mbp.service_api.enums.PaymentMethodEnum;
import com.d13k_mbp.service_api.enums.PaymentStatusEnum;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {
    private OrderStatusEnum orderStatus;
    private int orderDiscount;
    private PaymentStatusEnum paymentStatus;
    private PaymentMethodEnum paymentMethod;
    private List<RequestOrderItemDTO> orderItems;
}
