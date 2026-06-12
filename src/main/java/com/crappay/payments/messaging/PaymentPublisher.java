package com.crappay.payments.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishApproved(
            PaymentApprovedMessage message
    ) {

        rabbitTemplate.convertAndSend(
                RabbitConstants.PAYMENT_EXCHANGE,
                RabbitConstants.PAYMENT_APPROVED_ROUTING_KEY,
                message
        );
    }

    public void publishRejected(
            PaymentRejectedMessage message
    ) {

        rabbitTemplate.convertAndSend(
                RabbitConstants.PAYMENT_EXCHANGE,
                RabbitConstants.PAYMENT_REJECTED_ROUTING_KEY,
                message
        );
    }
}