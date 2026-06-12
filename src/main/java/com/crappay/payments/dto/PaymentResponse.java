package com.crappay.payments.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentResponse {
    private String redirectUrl;
    private String paymentReference;
    private String status;

}
