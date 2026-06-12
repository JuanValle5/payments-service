package com.crappay.payments;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;

import jakarta.annotation.PostConstruct;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.access-token}")
    private String accessToken;

    @PostConstruct
    public void init() {
        MercadoPagoConfig.setAccessToken(accessToken);
    }

    public Preference createPreference(String orderId, Double totalAmount, String description, String paymentReference) throws Exception {
        PreferenceItemRequest item = PreferenceItemRequest.builder()
            .id(orderId)
            .title(description)
            .quantity(1)
            .unitPrice(BigDecimal.valueOf(totalAmount))
            .build();

        PreferenceRequest request = PreferenceRequest.builder()
            .items(List.of(item))
            .externalReference(paymentReference)
            .build();

        PreferenceClient client = new PreferenceClient();
        return client.create(request);
    }
}
