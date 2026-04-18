package com.example.aiadventure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeepSeekChatRequest {
    private String model;
    private List<DeepSeekMessage> messages;
    private Double temperature;
    private Integer maxTokens;
}
