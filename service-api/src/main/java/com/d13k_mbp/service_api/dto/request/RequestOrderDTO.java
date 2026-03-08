package com.d13k_mbp.service_api.dto.request;

import com.d13k_mbp.service_api.enums.OrderStatusEnum;
import com.d13k_mbp.service_api.enums.PaymentMethodEnum;
import com.d13k_mbp.service_api.enums.PaymentStatusEnum;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {
    private OrderStatusEnum orderStatus;
    private BigDecimal subTotal;
    private BigDecimal grandTotal;
    private int orderDiscount;
    private PaymentStatusEnum paymentStatus;
    private PaymentMethodEnum paymentMethod;
}
