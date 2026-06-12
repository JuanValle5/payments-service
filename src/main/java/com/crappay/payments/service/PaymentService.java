package com.crappay.payments.service;

import com.crappay.payments.model.Payment;

public interface PaymentService {

    Payment approvePayment(
            String paymentReference
    );

    Payment rejectPayment(
            String paymentReference
    );
}