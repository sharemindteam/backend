package com.example.sharemind.message.dto.response;

import com.example.sharemind.message.domain.Message;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponse {

    private final Boolean isCustomer;
    private final String content;
    private final LocalDateTime createdAt;

    @Builder
    public MessageResponse(Boolean isCustomer, String content, LocalDateTime createdAt) {
        this.isCustomer = isCustomer;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static MessageResponse from(Message message) {
        return MessageResponse.builder()
                .isCustomer(message.getIsCustomer())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
