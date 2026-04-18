package com.example.aiadventure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeepSeekChatResponse {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<DeepSeekChoice> choices;
    private DeepSeekUsage usage;
}
