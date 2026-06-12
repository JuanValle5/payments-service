package com.crappay.payments.messaging;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRejectedMessage {

    private UUID orderId;

    private UUID paymentId;
}