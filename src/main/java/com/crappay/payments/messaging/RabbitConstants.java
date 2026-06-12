package com.crappay.payments.messaging;

public class RabbitConstants {

    public static final String PAYMENT_EXCHANGE =
            "payment.exchange";

    public static final String PAYMENT_APPROVED_ROUTING_KEY =
            "payment.approved";

    public static final String PAYMENT_REJECTED_ROUTING_KEY =
            "payment.rejected";

    public static final String PAYMENT_APPROVED_QUEUE =
            "payment.approved.queue";

    public static final String PAYMENT_REJECTED_QUEUE =
            "payment.rejected.queue";

    private RabbitConstants() {
    }
}