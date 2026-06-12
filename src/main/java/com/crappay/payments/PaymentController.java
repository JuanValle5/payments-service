package com.crappay.payments;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        PaymentAttempt attempt = new PaymentAttempt();
        attempt.setId(UUID.randomUUID().toString());
        attempt.setOrderId(request.getOrderId());
        attempt.setTotalAmount(request.getTotalAmount());
        attempt.setPaymentReference(paymentReference);
        attempt.setStatus("PENDING");
        attempt.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(attempt);

        Preference preference = mercadoPagoService.createPreference(
            request.getOrderId(), request.getTotalAmount(), request.getDescription(), paymentReference
        );

        PaymentResponse response = new PaymentResponse();
        response.setRedirectUrl(preference.getInitPoint());
        response.setPaymentReference(paymentReference);
        response.setStatus("PENDING");

        return response;
    }
}
