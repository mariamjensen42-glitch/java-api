package com.example.aiadventure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeepSeekChoice {
    private Integer index;
    private DeepSeekMessage message;
    private String finishReason;
}
