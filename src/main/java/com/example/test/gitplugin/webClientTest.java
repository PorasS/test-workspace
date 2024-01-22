package com.example.test.gitplugin;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class webClientTest {

    private static WebClient client;

    public static WebClient getClient() {
        if (client == null) {
            ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                    .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(50 * 1024 * 1024)).build();
            client = WebClient.builder().exchangeStrategies(exchangeStrategies).build();
        }
        return client;
    }

    public void createRemoteBranch(String url) throws Exception {
        WebClient client = null;

        try {
            client = getClient();

            url += "/git/refs";
            String request = "{\"ref\":\"refs/heads/GIT_Webhook_gyThree_05-09_16836154835\",\"sha\":\"5dd0135d0140248b5f90dcb37371e0db9865a62b\"}";
            String bearer = "Bearer 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";
            String response = client.post().uri(url).header(HttpHeaders.AUTHORIZATION, bearer)
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).bodyValue(request)
                    .retrieve().onStatus(HttpStatus::isError, this::handleErrorResponse).bodyToMono(String.class).block();

            System.out.println(response);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public void cloneProgram() throws Exception {
        WebClient client = null;

        try {
            client = getClient();

            String url = "https://987-ZFB-679.mktorest.com/rest/asset/v1/program/1679/clone.json?access_token=47ad135b-dd44-4395-a146-0fc1e08dabec:ab&name=GCTS-12382+clone_fr-FR&folder=%7B%22id%22%3A%22559%22%2C%22type%22%3A%22Folder%22%7D";
            String response = client.post().uri(url)
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                    .retrieve().onStatus(HttpStatus::isError, this::handleErrorResponse).bodyToMono(String.class).block();

            System.out.println(response);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static void updateDynamicContent() {
        // Create a WebClient instance
        WebClient webClient = getClient();

        // Define the form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("type", "Image");
        formData.add("segment", "es-es");
        formData.add("altText", "Icono+de+IA+generativa+para+todas+las+startups");
        formData.add("externalUrl", "https://pages.awscloud.com/rs/112-TZM-766/images/Responsible%20AI_3.png?version=0");

        // Make the request
        ResponseEntity<String> responseEntity = webClient.post()
                .uri("https://112-TZM-766.mktorest.com/rest/asset/v1/email/922802/dynamicContent/RVMtTGVmdC1JY29uMTBlYzNhOTAzLTRmZGMtNDNiNy04ZTg5LTZmNjA5NzIyNjJhYg==.json?access_token=c7ba2e1e-2bee-4db9-81b0-d4f60fda2bad:sj")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .toEntity(String.class)
                .block(); // block() is used here for simplicity; in a real application, consider using subscribe()

        // Print the response
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
    }

    public Mono<? extends Throwable> handleErrorResponse(ClientResponse response) {
        if (response.statusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
            return response.bodyToMono(String.class)
                    .flatMap(errorBody -> Mono.error(new Exception("Request failed with status code: " + response.statusCode() + ", Error: " + errorBody)));
        } else {
            return Mono.error(new Exception("Request failed with status code: " + response.statusCode()));
        }
    }

    public static void main(String[] args) throws Exception {
        webClientTest webClientTest = new webClientTest();
//        webClientTest.createRemoteBranch("https://api.github.com/repos/devpune/TestRepo");
//        webClientTest.cloneProgram();
        webClientTest.updateDynamicContent();
    }


}
