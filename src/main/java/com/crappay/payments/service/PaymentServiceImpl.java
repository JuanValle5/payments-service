package com.crappay.payments.service;

import com.crappay.payments.messaging.PaymentApprovedMessage;
import com.crappay.payments.messaging.PaymentPublisher;
import com.crappay.payments.messaging.PaymentRejectedMessage;
import com.crappay.payments.model.Payment;
import com.crappay.payments.model.PaymentStatus;
import com.crappay.payments.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl
        implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentPublisher paymentPublisher;

    @Override
    public Payment approvePayment(
            String paymentReference
    ) {

        Payment payment =
                paymentRepository
                        .findByPaymentReference(
                                paymentReference
                        )
                        .orElseThrow();

        payment.setStatus(
                PaymentStatus.APPROVED
        );

        Payment savedPayment =
                paymentRepository.save(
                        payment
                );

        paymentPublisher.publishApproved(
                PaymentApprovedMessage.builder()
                        .orderId(
                                savedPayment.getOrderId()
                        )
                        .paymentId(
                                savedPayment.getId()
                        )
                        .build()
        );

        return savedPayment;
    }

    @Override
    public Payment rejectPayment(
            String paymentReference
    ) {

        Payment payment =
                paymentRepository
                        .findByPaymentReference(
                                paymentReference
                        )
                        .orElseThrow();

        payment.setStatus(
                PaymentStatus.REJECTED
        );

        Payment savedPayment =
                paymentRepository.save(
                        payment
                );

        paymentPublisher.publishRejected(
                PaymentRejectedMessage.builder()
                        .orderId(
                                savedPayment.getOrderId()
                        )
                        .paymentId(
                                savedPayment.getId()
                        )
                        .build()
        );

        return savedPayment;
    }
}