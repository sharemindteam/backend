package com.example.sharemind.message.dto.response;

import com.example.sharemind.message.domain.Message;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageResponse {

    private final Boolean isCustomer;
    private final String content;

    @Builder
    public MessageResponse(Boolean isCustomer, String content, LocalDateTime createdAt) {
        this.isCustomer = isCustomer;
        this.content = content;
    }

    public static MessageResponse from(Message message) {
        return MessageResponse.builder()
                .isCustomer(message.getIsCustomer())
                .content(message.getContent())
                .build();
    }
}
