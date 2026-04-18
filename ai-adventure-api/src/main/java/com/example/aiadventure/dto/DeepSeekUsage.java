package com.example.aiadventure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeepSeekUsage {
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
}
