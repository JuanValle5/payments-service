package com.crappay.payments.messaging;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange paymentExchange() {

        return new TopicExchange(
                RabbitConstants.PAYMENT_EXCHANGE
        );
    }

    @Bean
    public Queue paymentApprovedQueue() {

        return new Queue(
                RabbitConstants.PAYMENT_APPROVED_QUEUE
        );
    }
    @Bean
    public Queue paymentRejectedQueue() {

        return new Queue(
                RabbitConstants.PAYMENT_REJECTED_QUEUE
        );
    }
    @Bean
    public Binding paymentApprovedBinding(
            Queue paymentApprovedQueue,
            TopicExchange paymentExchange
    ) {

        return BindingBuilder
                .bind(paymentApprovedQueue)
                .to(paymentExchange)
                .with(
                        RabbitConstants
                                .PAYMENT_APPROVED_ROUTING_KEY
                );
    }
    @Bean
    public Binding paymentRejectedBinding(
            Queue paymentRejectedQueue,
            TopicExchange paymentExchange
    ) {

        return BindingBuilder
                .bind(paymentRejectedQueue)
                .to(paymentExchange)
                .with(
                        RabbitConstants
                                .PAYMENT_REJECTED_ROUTING_KEY
                );
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            JacksonJsonMessageConverter converter
    ) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(
                connectionFactory
        );

        factory.setMessageConverter(
                converter
        );

        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            JacksonJsonMessageConverter converter
    ) {

        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(
                converter
        );

        return rabbitTemplate;
    }

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter() {

        return new JacksonJsonMessageConverter();
    }
}
