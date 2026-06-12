package com.crappay.payments.messaging;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange paymentExchange() {

        return new TopicExchange(
                RabbitConstants.PAYMENT_EXCHANGE
        );
    }
}
