package com.example.sharemind.message.dto.response;

import com.example.sharemind.message.domain.Message;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageResponse {

    private final Boolean isSender;
    private final String content;

    @Builder
    public MessageResponse(Boolean isSender, String content) {
        this.isSender = isSender;
        this.content = content;
    }

    public static MessageResponse from(Message message) {
        return MessageResponse.builder()
                .isSender(message.getIsSender())
                .content(message.getContent())
                .build();
    }
}
