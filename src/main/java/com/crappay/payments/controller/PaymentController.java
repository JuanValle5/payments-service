package com.crappay.payments.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import com.crappay.payments.service.MercadoPagoService;
import com.crappay.payments.dto.PaymentResponse;
import com.crappay.payments.dto.PaymentRequest;
import com.crappay.payments.model.Payment;
import com.crappay.payments.model.PaymentStatus;
import com.crappay.payments.repository.PaymentRepository;
import org.springframework.web.bind.annotation.*;

import com.mercadopago.resources.preference.Preference;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final MercadoPagoService mercadoPagoService;
    private final PaymentRepository paymentRepository;

    public PaymentController(MercadoPagoService mercadoPagoService, PaymentRepository paymentRepository) {
        this.mercadoPagoService = mercadoPagoService;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/initiate")
    public PaymentResponse initiatePayment(@RequestBody PaymentRequest request) throws Exception {
        String paymentReference = UUID.randomUUID().toString();

        Payment payment =
                Payment.builder()
                        .orderId(request.getOrderId())
                        .amount(request.getTotalAmount())
                        .paymentReference(paymentReference)
                        .status(PaymentStatus.PENDING)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

        paymentRepository.save(payment);

        Preference preference = mercadoPagoService.createPreference(
            request.getOrderId(), request.getTotalAmount(), request.getDescription(), paymentReference
        );

        PaymentResponse response = new PaymentResponse();
        response.setRedirectUrl(preference.getInitPoint());
        response.setPaymentReference(paymentReference);
        response.setStatus("PENDING");

        return response;
    }

    @GetMapping("/{paymentReference}")
    public Payment getPayment(
            @PathVariable String paymentReference
    ) {

        return paymentRepository
                .findByPaymentReference(
                        paymentReference
                )
                .orElseThrow();
    }
}
