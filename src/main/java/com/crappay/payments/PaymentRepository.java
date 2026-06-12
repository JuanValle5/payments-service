package com.crappay.payments;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {
    private final Map<String, PaymentAttempt> store = new ConcurrentHashMap<>();

    public PaymentAttempt save(PaymentAttempt attempt) {
        store.put(attempt.getId(), attempt);
        return attempt;
    }

    public Optional<PaymentAttempt> findByPaymentReference(String paymentReference) {
        return store.values().stream()
            .filter(a -> a.getPaymentReference().equals(paymentReference))
            .findFirst();
    }
}
