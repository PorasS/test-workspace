package com.example.test.gitplugin;

import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientFactory {

    private static WebClient webClient;

    public static WebClient getClient() {
        if (webClient == null) {
            ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                    .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(50 * 1024 * 1024)).build();
            webClient = WebClient.builder().exchangeStrategies(exchangeStrategies).build();
        }
        return webClient;
    }
}
