package com.example.aiadventure.service;

import com.example.aiadventure.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Collections;

@Service
public class DeepSeekClient {

    private final WebClient webClient;
    private final String apiKey;
    private final String baseUrl;
    private final String model;
    private final Double temperature;
    private final Integer maxTokens;

    public DeepSeekClient(
            @Value("${deepseek.api.key}") String apiKey,
            @Value("${deepseek.api.base-url}") String baseUrl,
            @Value("${deepseek.api.model:deepseek-chat}") String model,
            @Value("${deepseek.api.temperature:0.7}") Double temperature,
            @Value("${deepseek.api.max-tokens:2000}") Integer maxTokens) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.model = model;
        this.temperature = temperature;
        this.maxTokens = maxTokens;

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }

    public String generateStory(String prompt) {
        DeepSeekMessage message = new DeepSeekMessage("user", "请生成一个精彩的冒险故事：" + prompt);
        return sendChatRequest(Collections.singletonList(message));
    }

    public String generateDialogue(String context, String character) {
        String userPrompt = String.format(
                "基于以下上下文，生成角色 '%s' 的对话：\n\n上下文：\n%s",
                character, context
        );
        DeepSeekMessage message = new DeepSeekMessage("user", userPrompt);
        return sendChatRequest(Collections.singletonList(message));
    }

    private String sendChatRequest(java.util.List<DeepSeekMessage> messages) {
        DeepSeekChatRequest request = DeepSeekChatRequest.builder()
                .model(model)
                .messages(messages)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .build();

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DeepSeekChatResponse.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                        .filter(this::isRetryableException))
                .map(response -> {
                    if (response.getChoices() != null && !response.getChoices().isEmpty()) {
                        return response.getChoices().get(0).getMessage().getContent();
                    }
                    throw new RuntimeException("No response from DeepSeek API");
                })
                .onErrorMap(e -> new RuntimeException("Failed to call DeepSeek API: " + e.getMessage(), e))
                .block();
    }

    private boolean isRetryableException(Throwable throwable) {
        if (throwable instanceof WebClientResponseException) {
            WebClientResponseException ex = (WebClientResponseException) throwable;
            int statusCode = ex.getStatusCode().value();
            return statusCode == 429 || statusCode >= 500;
        }
        return true;
    }
}
